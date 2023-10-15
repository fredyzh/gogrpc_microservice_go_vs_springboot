package v1

import (
	"context"
	"log/slog"

	gw "github.com/fredyzh/ecom/product/api/v1/proto"
	conf "github.com/fredyzh/ecom/product/app/config/v1"
	svc "github.com/fredyzh/ecom/product/app/internal/service/v1"
	"github.com/grpc-ecosystem/grpc-gateway/v2/runtime"
)

func NewHTTPServer(c *conf.Server_HTTP, s *svc.ProductService) *runtime.ServeMux {
	ctx := context.Background()
	ctx, cancel := context.WithTimeout(ctx, c.Timeout.AsDuration())
	defer cancel()

	mux := runtime.NewServeMux()
	err := gw.RegisterProductServiceHandlerServer(ctx, mux, s)
	if err != nil {
		slog.Error("err register token http server: %v\n", err)
		return nil
	}

	return mux
}
