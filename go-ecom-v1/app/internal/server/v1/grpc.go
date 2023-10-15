package v1

import (
	pb "github.com/fredyzh/ecom/product/api/v1/proto"
	conf "github.com/fredyzh/ecom/product/app/config/v1"
	svc "github.com/fredyzh/ecom/product/app/internal/service/v1"
	"google.golang.org/grpc"
)

func NewGRPCServer(c *conf.Server_GRPC, s *svc.ProductService) *grpc.Server {
	opts := []grpc.ServerOption{}

	if c.Timeout != nil {
		opts = append(opts, grpc.ConnectionTimeout(c.Timeout.AsDuration()))
		opts = append(opts, grpc.MaxRecvMsgSize(int(c.MaxRecvMsgSize)))
		opts = append(opts, grpc.MaxSendMsgSize(int(c.MaxSendMsgSize)))
		opts = append(opts, grpc.MaxConcurrentStreams(uint32(c.MaxConcurrentStreams)))
	}

	svr := grpc.NewServer(opts...)
	pb.RegisterProductServiceServer(svr, s)

	return svr
}
