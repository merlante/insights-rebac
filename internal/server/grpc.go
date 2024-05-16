package server

import (
	h "ciam-rebac/api/health/v1"
	v1 "ciam-rebac/api/rebac/v1"
	"ciam-rebac/internal/conf"
	"ciam-rebac/internal/service"

	"github.com/go-kratos/kratos/v2/log"
	"github.com/go-kratos/kratos/v2/middleware/recovery"
	"github.com/go-kratos/kratos/v2/transport/grpc"
)

// NewGRPCServer new a gRPC server.
func NewGRPCServer(c *conf.Server, relations *service.RelationshipsService, health *service.HealthService, check *service.CheckService, subjects *service.LookupService, logger log.Logger) *grpc.Server {
	var opts = []grpc.ServerOption{
		grpc.Middleware(
			recovery.Recovery(),
		),
	}
	if c.Grpc.Network != "" {
		opts = append(opts, grpc.Network(c.Grpc.Network))
	}
	if c.Grpc.Addr != "" {
		opts = append(opts, grpc.Address(c.Grpc.Addr))
	}
	if c.Grpc.Timeout != nil {
		opts = append(opts, grpc.Timeout(c.Grpc.Timeout.AsDuration()))
	}
	srv := grpc.NewServer(opts...)
	v1.RegisterRelationshipsServer(srv, relations)
	v1.RegisterCheckServer(srv, check)
	h.RegisterHealthServer(srv, health)
	v1.RegisterLookupServer(srv, subjects)
	return srv
}
