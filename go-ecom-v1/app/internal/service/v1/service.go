package v1

import (
	svr "github.com/fredyzh/ecom/product/api/v1/proto"
	bus "github.com/fredyzh/ecom/product/app/internal/business/v1"
)

type ProductService struct {
	svr.UnimplementedProductServiceServer

	UB bus.ProductBusiness
}
