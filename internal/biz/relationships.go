package biz

import (
	v1 "ciam-rebac/api/rebac/v1"
	"context"

	"github.com/go-kratos/kratos/v2/log"
)

// relationship domain objects re-used from the api layer for now, but otherwise would be defined here
type TouchSemantics bool

type ContinuationToken string
type SubjectResult struct {
	Subject      *v1.SubjectReference
	Continuation ContinuationToken
}

type ZanzibarRepository interface {
	Check(ctx context.Context, request *v1.CheckRequest) (*v1.CheckResponse, error)
	LookupSubjects(ctx context.Context, subject_type, subject_relation, relation string, object *v1.ObjectReference, limit uint32, continuation ContinuationToken) (chan *SubjectResult, chan error, error)
	CreateRelationships(context.Context, []*v1.Relationship, TouchSemantics) error
	ReadRelationships(context.Context, *v1.RelationshipFilter) ([]*v1.Relationship, error)
	DeleteRelationships(context.Context, *v1.RelationshipFilter) error
}

type CheckUsecase struct {
	repo ZanzibarRepository
	log  *log.Helper
}

func NewCheckUsecase(repo ZanzibarRepository, logger log.Logger) *CheckUsecase {
	return &CheckUsecase{repo: repo, log: log.NewHelper(logger)}
}

func (rc *CheckUsecase) Check(ctx context.Context, check *v1.CheckRequest) (*v1.CheckResponse, error) {
	rc.log.WithContext(ctx).Infof("Check: %v", check)
	return rc.repo.Check(ctx, check)
}

type CreateRelationshipsUsecase struct {
	repo ZanzibarRepository
	log  *log.Helper
}

func NewCreateRelationshipsUsecase(repo ZanzibarRepository, logger log.Logger) *CreateRelationshipsUsecase {
	return &CreateRelationshipsUsecase{repo: repo, log: log.NewHelper(logger)}
}

func (rc *CreateRelationshipsUsecase) CreateRelationships(ctx context.Context, r []*v1.Relationship, touch bool) error {
	rc.log.WithContext(ctx).Infof("CreateRelationships: %v %s", r, touch)
	return rc.repo.CreateRelationships(ctx, r, TouchSemantics(touch))
}

type ReadRelationshipsUsecase struct {
	repo ZanzibarRepository
	log  *log.Helper
}

func NewReadRelationshipsUsecase(repo ZanzibarRepository, logger log.Logger) *ReadRelationshipsUsecase {
	return &ReadRelationshipsUsecase{repo: repo, log: log.NewHelper(logger)}
}

func (rc *ReadRelationshipsUsecase) ReadRelationships(ctx context.Context, r *v1.RelationshipFilter) ([]*v1.Relationship, error) {
	rc.log.WithContext(ctx).Infof("ReadRelationships: %v", r)
	return rc.repo.ReadRelationships(ctx, r)
}

type DeleteRelationshipsUsecase struct {
	repo ZanzibarRepository
	log  *log.Helper
}

func NewDeleteRelationshipsUsecase(repo ZanzibarRepository, logger log.Logger) *DeleteRelationshipsUsecase {
	return &DeleteRelationshipsUsecase{repo: repo, log: log.NewHelper(logger)}
}

func (rc *DeleteRelationshipsUsecase) DeleteRelationships(ctx context.Context, r *v1.RelationshipFilter) error {
	rc.log.WithContext(ctx).Infof("DeleteRelationships: %v", r)
	return rc.repo.DeleteRelationships(ctx, r)
}
