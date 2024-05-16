package server

import (
	h "ciam-rebac/api/health/v1"
	v1 "ciam-rebac/api/rebac/v1"
	"ciam-rebac/internal/conf"
	"ciam-rebac/internal/service"

	"github.com/go-kratos/kratos/v2/log"
	"github.com/go-kratos/kratos/v2/middleware/recovery"
	"github.com/go-kratos/kratos/v2/transport/http"
)

// NewHTTPServer new an HTTP server.
func NewHTTPServer(c *conf.Server, relationships *service.RelationshipsService, health *service.HealthService, check *service.CheckService, subjects *service.LookupService, logger log.Logger) *http.Server {
	var opts = []http.ServerOption{
		http.Middleware(
			recovery.Recovery(),
		),
	}
	if c.Http.Network != "" {
		opts = append(opts, http.Network(c.Http.Network))
	}
	if c.Http.Addr != "" {
		opts = append(opts, http.Address(c.Http.Addr))
	}
	if c.Http.Timeout != nil {
		opts = append(opts, http.Timeout(c.Http.Timeout.AsDuration()))
	}
	if c.Http.Pathprefix != "" {
		opts = append(opts, http.PathPrefix(c.Http.Pathprefix))
	}

	srv := http.NewServer(opts...)

	v1.RegisterRelationshipsHTTPServer(srv, relationships)
	v1.RegisterCheckHTTPServer(srv, check)
	h.RegisterHealthHTTPServer(srv, health)

	return srv
}
