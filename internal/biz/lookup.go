package biz

import (
	v1 "ciam-rebac/api/rebac/v1"
	"context"

	"github.com/go-kratos/kratos/v2/errors"
	"github.com/go-kratos/kratos/v2/log"
)

const (
	MaxStreamingCount uint32 = 1000
)

type GetSubjectsUsecase struct {
	repo ZanzibarRepository
	log  *log.Helper
}

func NewGetSubjectsUseCase(repo ZanzibarRepository, logger log.Logger) *GetSubjectsUsecase {
	return &GetSubjectsUsecase{repo: repo, log: log.NewHelper(logger)}
}

func (s *GetSubjectsUsecase) Get(ctx context.Context, req *v1.LookupSubjectsRequest) (chan *SubjectResult, chan error, error) {
	limit := uint32(MaxStreamingCount)
	if req.Limit != nil && *req.Limit < limit {
		limit = *req.Limit
	}

	continuation := ContinuationToken("")
	if req.ContinuationToken != nil {
		continuation = ContinuationToken(*req.ContinuationToken)
	}

	if req.Object == nil {
		return nil, nil, errors.BadRequest("Invalid request", "Object is required")
	}

	subs, errs, err := s.repo.LookupSubjects(ctx, req.SubjectType, req.SubjectRelation, req.Relation, &v1.ObjectReference{
		Type: req.Object.Type, //Need null check
		Id:   req.Object.Id,
	}, limit, continuation)

	if err != nil {
		return nil, nil, err
	}

	return subs, errs, nil
}
