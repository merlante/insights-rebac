package service

import (
	v1 "ciam-rebac/api/rebac/v1"
	"ciam-rebac/internal/biz"
	"ciam-rebac/internal/data"
	"context"
	"os"
	"testing"

	"github.com/go-kratos/kratos/v2/log"
	"github.com/go-kratos/kratos/v2/middleware/tracing"
	"github.com/stretchr/testify/assert"
	"google.golang.org/grpc"
)

func TestLookupService_LookupSubjects_EmptyRequest(t *testing.T) {
	t.Parallel()
	ctx := context.TODO()
	spicedb, err := container.CreateSpiceDbRepository()
	assert.NoError(t, err)
	service := createLookupService(spicedb)
	responseCollector := NewLookup_SubjectsServerStub(ctx)
	err = service.Subjects(&v1.LookupSubjectsRequest{}, responseCollector)

	assert.Error(t, err)
}

func TestLookupService_LookupSubjects_NoResults(t *testing.T) {
	t.Parallel()
	ctx := context.TODO()
	spicedb, err := container.CreateSpiceDbRepository()
	assert.NoError(t, err)

	err = seedThingInDefaultWorkspace(ctx, spicedb, "thing1")
	assert.NoError(t, err)

	service := createLookupService(spicedb)

	responseCollector := NewLookup_SubjectsServerStub(ctx)
	err = service.Subjects(&v1.LookupSubjectsRequest{
		SubjectType: "user",
		Relation:    "view",
		Object:      &v1.ObjectReference{Type: "thing", Id: "thing1"},
	}, responseCollector)
	assert.NoError(t, err)
	results := responseCollector.GetResponses()

	assert.Empty(t, results)
}

func TestLookupService_LookupSubjects_OneResult(t *testing.T) {
	t.Parallel()
	ctx := context.TODO()
	spicedb, err := container.CreateSpiceDbRepository()
	assert.NoError(t, err)

	err = seedThingInDefaultWorkspace(ctx, spicedb, "thing1")
	assert.NoError(t, err)
	err = seedUserWithViewThingInDefaultWorkspace(ctx, spicedb, "u1")
	assert.NoError(t, err)

	assert.NoError(t, err)

	service := createLookupService(spicedb)

	responseCollector := NewLookup_SubjectsServerStub(ctx)
	err = service.Subjects(&v1.LookupSubjectsRequest{
		SubjectType: "user",
		Relation:    "view",
		Object:      &v1.ObjectReference{Type: "thing", Id: "thing1"},
	}, responseCollector)
	assert.NoError(t, err)
	ids := responseCollector.GetIDs()

	assert.ElementsMatch(t, []string{"u1"}, ids)
}

func TestLookupService_LookupSubjects_TwoResults(t *testing.T) {
	t.Parallel()
	ctx := context.TODO()
	spicedb, err := container.CreateSpiceDbRepository()
	assert.NoError(t, err)

	err = seedThingInDefaultWorkspace(ctx, spicedb, "thing1")
	assert.NoError(t, err)
	err = seedUserWithViewThingInDefaultWorkspace(ctx, spicedb, "u1")
	assert.NoError(t, err)
	err = seedUserWithViewThingInDefaultWorkspace(ctx, spicedb, "u2")
	assert.NoError(t, err)

	service := createLookupService(spicedb)

	responseCollector := NewLookup_SubjectsServerStub(ctx)
	err = service.Subjects(&v1.LookupSubjectsRequest{
		SubjectType: "user",
		Relation:    "view",
		Object:      &v1.ObjectReference{Type: "thing", Id: "thing1"},
	}, responseCollector)
	assert.NoError(t, err)
	ids := responseCollector.GetIDs()

	assert.ElementsMatch(t, []string{"u1", "u2"}, ids)
}

func createLookupService(spicedb *data.SpiceDbRepository) *LookupService {
	logger := log.With(log.NewStdLogger(os.Stdout),
		"ts", log.DefaultTimestamp,
		"caller", log.DefaultCaller,
		"trace.id", tracing.TraceID(),
		"span.id", tracing.SpanID(),
	)
	return NewLookupService(biz.NewGetSubjectsUseCase(spicedb, logger))
}
func seedThingInDefaultWorkspace(ctx context.Context, spicedb *data.SpiceDbRepository, thing string) error {
	return spicedb.CreateRelationships(ctx, []*v1.Relationship{
		{
			Object:   &v1.ObjectReference{Type: "thing", Id: thing},
			Relation: "workspace",
			Subject:  &v1.SubjectReference{Object: &v1.ObjectReference{Type: "workspace", Id: "default"}},
		},
	}, biz.TouchSemantics(true))
}

func seedUserWithViewThingInDefaultWorkspace(ctx context.Context, spicedb *data.SpiceDbRepository, user string) error {
	return spicedb.CreateRelationships(ctx, []*v1.Relationship{
		{
			Object:   &v1.ObjectReference{Type: "role", Id: "viewers"},
			Relation: "view_the_thing",
			Subject:  &v1.SubjectReference{Object: &v1.ObjectReference{Type: "user", Id: "*"}},
		},
		{
			Object:   &v1.ObjectReference{Type: "role_binding", Id: "default_viewers"},
			Relation: "subject",
			Subject:  &v1.SubjectReference{Object: &v1.ObjectReference{Type: "user", Id: user}},
		},
		{
			Object:   &v1.ObjectReference{Type: "role_binding", Id: "default_viewers"},
			Relation: "granted",
			Subject:  &v1.SubjectReference{Object: &v1.ObjectReference{Type: "role", Id: "viewers"}},
		},
		{
			Object:   &v1.ObjectReference{Type: "workspace", Id: "default"},
			Relation: "user_grant",
			Subject:  &v1.SubjectReference{Object: &v1.ObjectReference{Type: "role_binding", Id: "default_viewers"}},
		},
	}, biz.TouchSemantics(true))
}

func NewLookup_SubjectsServerStub(ctx context.Context) *Lookup_SubjectsServerStub {
	return &Lookup_SubjectsServerStub{
		ServerStream: nil,
		responses:    []*v1.LookupSubjectsResponse{},
		ctx:          ctx,
	}
}

func (s *Lookup_SubjectsServerStub) GetResponses() []*v1.LookupSubjectsResponse {
	return s.responses
}

func (s *Lookup_SubjectsServerStub) GetIDs() []string {
	ids := make([]string, len(s.responses))

	for i, r := range s.responses {
		ids[i] = r.Subject.Object.Id
	}

	return ids
}

type Lookup_SubjectsServerStub struct {
	grpc.ServerStream
	responses []*v1.LookupSubjectsResponse
	ctx       context.Context
}

func (s *Lookup_SubjectsServerStub) Context() context.Context {
	return s.ctx
}

func (s *Lookup_SubjectsServerStub) Send(r *v1.LookupSubjectsResponse) error {
	s.responses = append(s.responses, r)
	return nil
}
