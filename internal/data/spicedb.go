package data

import (
	apiV1 "ciam-rebac/api/rebac/v1"
	"ciam-rebac/internal/biz"
	"ciam-rebac/internal/conf"
	"context"
	"errors"
	"fmt"
	"io"
	"os"

	v1 "github.com/authzed/authzed-go/proto/authzed/api/v1"
	"github.com/authzed/authzed-go/v1"
	"github.com/authzed/grpcutil"
	"github.com/go-kratos/kratos/v2/log"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

// SpiceDbRepository .
type SpiceDbRepository struct {
	client *authzed.Client
}

// NewSpiceDbRepository .
func NewSpiceDbRepository(c *conf.Data, logger log.Logger) (*SpiceDbRepository, func(), error) {
	log.NewHelper(logger).Info("creating spicedb connection")

	var opts []grpc.DialOption
	opts = append(opts, grpc.EmptyDialOption{})
	//TODO: add a flag to enable/disable grpc.WithBlock

	var token string
	var err error
	if c.SpiceDb.Token != "" {
		token = c.SpiceDb.Token
	} else if c.SpiceDb.TokenFile != "" {
		token, err = readToken(c.SpiceDb.TokenFile)
		if err != nil {
			log.NewHelper(logger).Error(err)
			return nil, nil, err
		}
	}
	if token == "" {
		err := fmt.Errorf("token is empty: %s", token)
		log.NewHelper(logger).Error(err)
		return nil, nil, err
	}

	if !c.SpiceDb.UseTLS {
		opts = append(opts, grpcutil.WithInsecureBearerToken(token))
		opts = append(opts, grpc.WithTransportCredentials(insecure.NewCredentials()))
	} else {
		tlsConfig, _ := grpcutil.WithSystemCerts(grpcutil.VerifyCA)
		opts = append(opts, grpcutil.WithBearerToken(token))
		opts = append(opts, tlsConfig)
	}

	client, err := authzed.NewClient(
		c.SpiceDb.Endpoint,
		opts...,
	)

	if err != nil {
		err = fmt.Errorf("error creating spicedb client: %w", err)
		log.NewHelper(logger).Error(err)

		return nil, nil, err
	}

	cleanup := func() {
		log.NewHelper(logger).Info("spicedb connection cleanup requested (nothing to clean up)")
	}

	return &SpiceDbRepository{client}, cleanup, nil
}

func (s *SpiceDbRepository) LookupSubjects(ctx context.Context, subject_type string, relation string, object *apiV1.ObjectReference) (chan *apiV1.SubjectReference, chan error, error) {
	client, err := s.client.LookupSubjects(ctx, &v1.LookupSubjectsRequest{
		Resource: &v1.ObjectReference{
			ObjectType: object.Type,
			ObjectId:   object.Id,
		},
		Permission:              relation,
		SubjectObjectType:       subject_type,
		OptionalSubjectRelation: "",
		OptionalConcreteLimit:   0,
	})

	if err != nil {
		return nil, nil, err
	}

	subjects := make(chan *v1.SubjectReference)
	errs := make(chan error, 1)

	go func() {
		msg, err := client.Recv()
		if err != nil {
			if !errors.Is(err, io.EOF) {
				errs <- err
			}
			close(errs)
			close(subjects)
			return
		}

		subj := msg.GetSubject()
		subjects <- &v1.SubjectReference{
			Object: &v1.ObjectReference{
				ObjectType: subject_type,
				ObjectId:   subj.SubjectObjectId,
			},
			OptionalRelation: "",
		}
	}()

	return nil, nil, nil
}

func (s *SpiceDbRepository) CreateRelationships(ctx context.Context, rels []*apiV1.Relationship, touch biz.TouchSemantics) error {
	var relationshipUpdates []*v1.RelationshipUpdate

	var operation v1.RelationshipUpdate_Operation
	if touch {
		operation = v1.RelationshipUpdate_OPERATION_TOUCH
	} else {
		operation = v1.RelationshipUpdate_OPERATION_CREATE
	}

	for _, rel := range rels {
		relationshipUpdates = append(relationshipUpdates, &v1.RelationshipUpdate{
			Operation:    operation,
			Relationship: createSpiceDbRelationship(rel),
		})
	}

	_, err := s.client.WriteRelationships(ctx, &v1.WriteRelationshipsRequest{
		Updates: relationshipUpdates,
	})

	return err
}

func (s *SpiceDbRepository) ReadRelationships(ctx context.Context, filter *apiV1.RelationshipFilter) ([]*apiV1.Relationship, error) {
	req := &v1.ReadRelationshipsRequest{RelationshipFilter: createSpiceDbRelationshipFilter(filter)}

	client, err := s.client.ReadRelationships(ctx, req)

	if err != nil {
		return nil, err
	}

	results := make([]*apiV1.Relationship, 0)
	resp, err := client.Recv()
	for err == nil {
		results = append(results, &apiV1.Relationship{
			Object: &apiV1.ObjectReference{
				Type: resp.Relationship.Resource.ObjectType,
				Id:   resp.Relationship.Resource.ObjectId,
			},
			Relation: resp.Relationship.Relation,
			Subject: &apiV1.SubjectReference{
				Relation: resp.Relationship.Subject.OptionalRelation,
				Object: &apiV1.ObjectReference{
					Type: resp.Relationship.Subject.Object.ObjectType,
					Id:   resp.Relationship.Subject.Object.ObjectId,
				},
			},
		})

		resp, err = client.Recv()
	}

	if !errors.Is(err, io.EOF) {
		return nil, err
	}

	return results, nil
}

func (s *SpiceDbRepository) DeleteRelationships(ctx context.Context, filter *apiV1.RelationshipFilter) error {
	req := &v1.DeleteRelationshipsRequest{RelationshipFilter: createSpiceDbRelationshipFilter(filter)}

	_, err := s.client.DeleteRelationships(ctx, req)

	// TODO: we have not specified an option in our API to allow partial deletions, so currently it's all or nothing
	if err != nil {
		return err
	}

	return nil
}

func (s *SpiceDbRepository) Check(ctx context.Context, check *apiV1.CheckRequest) (*apiV1.CheckResponse, error) {
	subject := &v1.SubjectReference{
		Object: &v1.ObjectReference{
			ObjectType: check.GetSubject().GetObject().GetType(),
			ObjectId:   check.GetSubject().GetObject().GetId(),
		},
		OptionalRelation: check.GetSubject().GetRelation(),
	}

	object := &v1.ObjectReference{
		ObjectType: check.GetObject().GetType(),
		ObjectId:   check.GetObject().GetId(),
	}
	checkResponse, err := s.client.CheckPermission(ctx, &v1.CheckPermissionRequest{
		Resource:   object,
		Permission: check.GetRelation(),
		Subject:    subject,
	})
	if err != nil {
		log.Errorf("Error check permission %v", err.Error())
		return &apiV1.CheckResponse{Allowed: apiV1.CheckResponse_ALLOWED_UNSPECIFIED}, err
	}

	if checkResponse.Permissionship == v1.CheckPermissionResponse_PERMISSIONSHIP_HAS_PERMISSION {
		return &apiV1.CheckResponse{Allowed: apiV1.CheckResponse_ALLOWED_TRUE}, nil
	}

	return &apiV1.CheckResponse{Allowed: apiV1.CheckResponse_ALLOWED_FALSE}, nil
}

func createSpiceDbRelationshipFilter(filter *apiV1.RelationshipFilter) *v1.RelationshipFilter {
	spiceDbRelationshipFilter := &v1.RelationshipFilter{
		ResourceType:       filter.GetObjectType(),
		OptionalResourceId: filter.GetObjectId(),
		OptionalRelation:   filter.GetRelation(),
	}

	if filter.GetSubjectFilter() != nil {
		subjectFilter := &v1.SubjectFilter{
			SubjectType:       filter.GetSubjectFilter().GetSubjectType(),
			OptionalSubjectId: filter.GetSubjectFilter().GetSubjectId(),
		}

		if filter.GetSubjectFilter().GetRelation() != "" {
			subjectFilter.OptionalRelation = &v1.SubjectFilter_RelationFilter{
				Relation: filter.GetSubjectFilter().GetRelation(),
			}
		}

		spiceDbRelationshipFilter.OptionalSubjectFilter = subjectFilter
	}

	return spiceDbRelationshipFilter
}

func createSpiceDbRelationship(relationship *apiV1.Relationship) *v1.Relationship {
	subject := &v1.SubjectReference{
		Object: &v1.ObjectReference{
			ObjectType: relationship.GetSubject().GetObject().GetType(),
			ObjectId:   relationship.GetSubject().GetObject().GetId(),
		},
		OptionalRelation: relationship.GetSubject().GetRelation(),
	}

	object := &v1.ObjectReference{
		ObjectType: relationship.GetObject().GetType(),
		ObjectId:   relationship.GetObject().GetId(),
	}

	return &v1.Relationship{
		Resource: object,
		Relation: relationship.GetRelation(),
		Subject:  subject,
	}
}

func readToken(file string) (string, error) {
	isFileExist := checkFileExists(file)
	if !isFileExist {
		return file, errors.New("file doesn't exist")
	}
	bytes, err := os.ReadFile(file)
	if err != nil {
		return "", err
	}

	return string(bytes), nil
}

func checkFileExists(filePath string) bool {
	_, err := os.Stat(filePath)
	return !errors.Is(err, os.ErrNotExist)
}
