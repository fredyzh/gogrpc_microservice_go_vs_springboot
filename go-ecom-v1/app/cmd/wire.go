//go:build wireinject
// +build wireinject

package main

import (
	conf "github.com/fredyzh/ecom/product/app/config/v1"
	svr "github.com/fredyzh/ecom/product/app/internal/server/v1"
	svc "github.com/fredyzh/ecom/product/app/internal/service/v1"
	"github.com/google/wire"
)

func initApp(*conf.Server_GRPC, *conf.Server_HTTP, *conf.Data_Database, *svc.ProductService) (*App, error) {
	panic(wire.Build(svr.ProviderSet, newApp))
}
