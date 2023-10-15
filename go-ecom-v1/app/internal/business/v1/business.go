package v1

import (
	"context"

	pb "github.com/fredyzh/ecom/product/api/v1/proto"
	db "github.com/fredyzh/ecom/product/app/internal/data"
	"google.golang.org/protobuf/types/known/emptypb"
)

type ProductUseCase struct {
	DB db.DataRepo
}

type ProductBusiness interface {
	CreateProduct(context.Context, *pb.CreateProductRequest) (*pb.CreateProductResponse, error)
	ListAllProducts(context.Context, *emptypb.Empty) (*pb.ListAllProductsResponse, error)
	GetProducts(*emptypb.Empty, pb.ProductService_GetProductsServer) error
}
