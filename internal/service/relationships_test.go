package service

import (
	v1 "ciam-rebac/api/rebac/v1"
	"ciam-rebac/internal/biz"
	"ciam-rebac/internal/data"
	"context"
	"fmt"
	"github.com/go-kratos/kratos/v2/log"
	"github.com/go-kratos/kratos/v2/middleware/tracing"
	"github.com/stretchr/testify/assert"
	"google.golang.org/grpc"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/status"
	"os"
	"testing"
)

var container *data.LocalSpiceDbContainer

func TestMain(m *testing.M) {
	var err error
	logger := log.With(log.NewStdLogger(os.Stdout),
		"ts", log.DefaultTimestamp,
		"caller", log.DefaultCaller,
		"trace.id", tracing.TraceID(),
		"span.id", tracing.SpanID(),
	)

	container, err = data.CreateContainer(logger)

	if err != nil {
		fmt.Printf("Error initializing Docker container: %s", err)
		os.Exit(-1)
	}

	result := m.Run()

	container.Close()
	os.Exit(result)
}

func TestRelationshipsService_CreateRelationships(t *testing.T) {
	t.Parallel()
	err, relationshipsService := setup(t)
	assert.NoError(t, err)
	ctx := context.Background()
	expected := createRelationship("bob", "user", "", "member", "group", "bob_club")

	req := &v1.CreateRelationshipsRequest{
		Relationships: []*v1.Relationship{
			expected,
		},
	}
	_, err = relationshipsService.CreateRelationships(ctx, req)
	assert.NoError(t, err)

	readReq := &v1.ReadRelationshipsRequest{Filter: &v1.RelationshipFilter{
		ObjectId:   "bob_club",
		ObjectType: "group",
		Relation:   "member",
		SubjectFilter: &v1.SubjectFilter{
			SubjectId:   "bob",
			SubjectType: "user",
		},
	},
	}
	collectingServer := NewRelationships_ReadRelationshipsServerStub(ctx)
	err = relationshipsService.ReadRelationships(readReq, collectingServer)
	if err != nil {
		t.FailNow()
	}
	responseRelationships := collectingServer.responses

	for _, resp := range responseRelationships {
		assert.Equal(t, expected.Object.Id, resp.Relationship.Object.Id)
		assert.Equal(t, expected.Object.Type, resp.Relationship.Object.Type)
		assert.Equal(t, expected.Subject.Object.Id, resp.Relationship.Subject.Object.Id)
		assert.Equal(t, expected.Subject.Object.Type, resp.Relationship.Subject.Object.Type)
		assert.Equal(t, expected.Relation, resp.Relationship.Relation)
	}

}

func TestRelationshipsService_CreateRelationshipsWithTouchFalse(t *testing.T) {
	t.Parallel()
	err, relationshipsService := setup(t)
	assert.NoError(t, err)

	ctx := context.Background()
	expected := createRelationship("bob", "user", "", "member", "group", "bob_club")
	req := &v1.CreateRelationshipsRequest{
		Relationships: []*v1.Relationship{
			expected,
		},
	}
	_, err = relationshipsService.CreateRelationships(ctx, req)
	assert.NoError(t, err)

	readReq := &v1.ReadRelationshipsRequest{Filter: &v1.RelationshipFilter{
		ObjectId:   "bob_club",
		ObjectType: "group",
		Relation:   "member",
		SubjectFilter: &v1.SubjectFilter{
			SubjectId:   "bob",
			SubjectType: "user",
		},
	},
	}
	collectingServer := NewRelationships_ReadRelationshipsServerStub(ctx)
	err = relationshipsService.ReadRelationships(readReq, collectingServer)
	if err != nil {
		t.FailNow()
	}
	responseRelationships := collectingServer.responses

	for _, resp := range responseRelationships {
		assert.Equal(t, expected.Object.Id, resp.Relationship.Object.Id)
		assert.Equal(t, expected.Object.Type, resp.Relationship.Object.Type)
		assert.Equal(t, expected.Subject.Object.Id, resp.Relationship.Subject.Object.Id)
		assert.Equal(t, expected.Subject.Object.Type, resp.Relationship.Subject.Object.Type)
		assert.Equal(t, expected.Relation, resp.Relationship.Relation)
	}

	_, err = relationshipsService.CreateRelationships(ctx, req)
	assert.Equal(t, status.Convert(err).Code(), codes.AlreadyExists)

}

func TestRelationshipsService_CreateRelationshipsWithBadSubjectType(t *testing.T) {
	t.Parallel()
	err, relationshipsService := setup(t)
	assert.NoError(t, err)
	ctx := context.Background()
	badSubjectType := "not_a_user"
	expected := createRelationship("bob", badSubjectType, "", "member", "group", "bob_club")
	req := &v1.CreateRelationshipsRequest{
		Relationships: []*v1.Relationship{
			expected,
		},
	}
	_, err = relationshipsService.CreateRelationships(ctx, req)
	assert.Error(t, err)
	assert.Equal(t, status.Convert(err).Code(), codes.FailedPrecondition)
	assert.Contains(t, err.Error(), "object definition `"+badSubjectType+"` not found")
}

func TestRelationshipsService_CreateRelationshipsWithBadObjectType(t *testing.T) {
	t.Parallel()
	err, relationshipsService := setup(t)
	assert.NoError(t, err)
	ctx := context.Background()
	badObjectType := "not_an_object"
	expected := createRelationship("bob", "user", "", "member", badObjectType, "bob_club")
	req := &v1.CreateRelationshipsRequest{
		Relationships: []*v1.Relationship{
			expected,
		},
	}
	_, err = relationshipsService.CreateRelationships(ctx, req)
	assert.Error(t, err)
	assert.Equal(t, status.Convert(err).Code(), codes.FailedPrecondition)
	assert.Contains(t, err.Error(), "object definition `"+badObjectType+"` not found")
}

func TestRelationshipsService_DeleteRelationships(t *testing.T) {
	t.Parallel()
	err, relationshipsService := setup(t)
	assert.NoError(t, err)

	expected := createRelationship("bob", "user", "", "member", "group", "bob_club")

	ctx := context.Background()
	req := &v1.CreateRelationshipsRequest{
		Relationships: []*v1.Relationship{
			expected,
		},
	}
	_, err = relationshipsService.CreateRelationships(ctx, req)
	assert.NoError(t, err)

	delreq := &v1.DeleteRelationshipsRequest{Filter: &v1.RelationshipFilter{
		ObjectId:   "bob_club",
		ObjectType: "group",
		Relation:   "member",
		SubjectFilter: &v1.SubjectFilter{
			SubjectId:   "bob",
			SubjectType: "user",
		},
	}}
	_, err = relationshipsService.DeleteRelationships(ctx, delreq)
	assert.NoError(t, err)

	readReq := &v1.ReadRelationshipsRequest{Filter: &v1.RelationshipFilter{
		ObjectId:   "bob_club",
		ObjectType: "group",
		Relation:   "member",
		SubjectFilter: &v1.SubjectFilter{
			SubjectId:   "bob",
			SubjectType: "user",
		},
	},
	}

	collectingServer := NewRelationships_ReadRelationshipsServerStub(ctx)
	err = relationshipsService.ReadRelationships(readReq, collectingServer)
	if err != nil {
		t.FailNow()
	}
	responses := collectingServer.responses

	assert.Equal(t, 0, len(responses))
	assert.NoError(t, err)
}

func setup(t *testing.T) (error, *RelationshipsService) {
	logger := log.With(log.NewStdLogger(os.Stdout),
		"ts", log.DefaultTimestamp,
		"caller", log.DefaultCaller,
		"trace.id", tracing.TraceID(),
		"span.id", tracing.SpanID(),
	)
	spiceDbRepository, err := container.CreateSpiceDbRepository()
	assert.NoError(t, err)

	createRelationshipsUsecase := biz.NewCreateRelationshipsUsecase(spiceDbRepository, logger)
	readRelationshipsUsecase := biz.NewReadRelationshipsUsecase(spiceDbRepository, logger)
	deleteRelationshipsUsecase := biz.NewDeleteRelationshipsUsecase(spiceDbRepository, logger)
	relationshipsService := NewRelationshipsService(logger, createRelationshipsUsecase, readRelationshipsUsecase, deleteRelationshipsUsecase)
	return err, relationshipsService
}

func TestRelationshipsService_ReadRelationships(t *testing.T) {
	t.Parallel()
	ctx := context.TODO()

	logger := log.With(log.NewStdLogger(os.Stdout),
		"ts", log.DefaultTimestamp,
		"caller", log.DefaultCaller,
		"trace.id", tracing.TraceID(),
		"span.id", tracing.SpanID(),
	)
	spiceDbRepository, err := container.CreateSpiceDbRepository()
	assert.NoError(t, err)

	createRelationshipsUsecase := biz.NewCreateRelationshipsUsecase(spiceDbRepository, logger)
	readRelationshipsUsecase := biz.NewReadRelationshipsUsecase(spiceDbRepository, logger)
	deleteRelationshipsUsecase := biz.NewDeleteRelationshipsUsecase(spiceDbRepository, logger)
	relationshipsService := NewRelationshipsService(logger, createRelationshipsUsecase, readRelationshipsUsecase, deleteRelationshipsUsecase)

	expected := createRelationship("bob", "user", "", "member", "group", "bob_club")

	reqCr := &v1.CreateRelationshipsRequest{
		Relationships: []*v1.Relationship{
			expected,
		},
	}
	_, err = relationshipsService.CreateRelationships(ctx, reqCr)
	assert.NoError(t, err)

	req := &v1.ReadRelationshipsRequest{Filter: &v1.RelationshipFilter{
		ObjectId:   "bob_club",
		ObjectType: "group",
		Relation:   "member",
		SubjectFilter: &v1.SubjectFilter{
			SubjectId:   "bob",
			SubjectType: "user",
		},
	},
	}

	collectingServer := NewRelationships_ReadRelationshipsServerStub(ctx)
	err = relationshipsService.ReadRelationships(req, collectingServer)
	if err != nil {
		t.FailNow()
	}
	responses := collectingServer.responses

	assert.Equal(t, 1, len(responses))
	assert.NoError(t, err)
}

func createRelationship(subjectId string, subjectType string, subjectRelationship string, relationship string, objectType string, objectId string) *v1.Relationship {
	subject := &v1.SubjectReference{
		Object: &v1.ObjectReference{
			Type: subjectType,
			Id:   subjectId,
		},
		Relation: subjectRelationship,
	}

	object := &v1.ObjectReference{
		Type: objectType,
		Id:   objectId,
	}

	return &v1.Relationship{
		Object:   object,
		Relation: relationship,
		Subject:  subject,
	}
}

// Below is the boilerplate for creating test servers for streaming ReadRelationships rpc

func NewRelationships_ReadRelationshipsServerStub(ctx context.Context) *Relationships_ReadRelationshipsServerStub {
	return &Relationships_ReadRelationshipsServerStub{
		ServerStream: nil,
		responses:    []*v1.ReadRelationshipsResponse{},
		ctx:          ctx,
	}
}

type Relationships_ReadRelationshipsServerStub struct {
	grpc.ServerStream
	responses []*v1.ReadRelationshipsResponse
	ctx       context.Context
}

func (x *Relationships_ReadRelationshipsServerStub) Send(m *v1.ReadRelationshipsResponse) error {
	x.responses = append(x.responses, m)
	return nil
}

func (x *Relationships_ReadRelationshipsServerStub) Context() context.Context {
	return x.ctx
}
