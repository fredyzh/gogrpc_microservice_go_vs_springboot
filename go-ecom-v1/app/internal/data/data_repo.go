package data

import (
	"context"

	pb "github.com/fredyzh/ecom/product/api/v1/proto"
)

type DataRepo interface {
	ConnectDB() interface{}
	CreateProduct(ctx context.Context, prod *pb.Product) (interface{}, error)
	ListAllProducts(ctx context.Context) ([]*pb.Product, error)
	GetProducts(ctx context.Context, stream pb.ProductService_GetProductsServer) error
}
