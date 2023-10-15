package v1

import (
	"context"

	pb "github.com/fredyzh/ecom/product/api/v1/proto"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"google.golang.org/protobuf/types/known/emptypb"
)

func (pc ProductUseCase) CreateProduct(ctx context.Context, req *pb.CreateProductRequest) (*pb.CreateProductResponse, error) {
	ret, err := pc.DB.CreateProduct(ctx, req.GetProduct())
	if err != nil {
		return nil, err
	}
	return &pb.CreateProductResponse{
		Id: (ret.(primitive.ObjectID)).String(),
	}, nil
}

func (pc ProductUseCase) ListAllProducts(ctx context.Context, req *emptypb.Empty) (*pb.ListAllProductsResponse, error) {
	prods, err := pc.DB.ListAllProducts(ctx)
	if err != nil {
		return nil, err
	}

	return &pb.ListAllProductsResponse{
		Products: prods,
	}, nil
}

func (pc ProductUseCase) GetProducts(req *emptypb.Empty, stream pb.ProductService_GetProductsServer) error {
	err := pc.DB.GetProducts(context.Background(), stream)
	if err != nil {
		return err
	}

	return nil
}
