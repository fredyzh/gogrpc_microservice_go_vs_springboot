package v1

import (
	"context"
	"log/slog"

	pb "github.com/fredyzh/ecom/product/api/v1/proto"
	"google.golang.org/protobuf/types/known/emptypb"
)

func (s *ProductService) Health(ctx context.Context, empt *emptypb.Empty) (*pb.HealthReply, error) {
	slog.Debug("health called")
	return &pb.HealthReply{
		Ok: true,
	}, nil
}

func (s *ProductService) CreateProduct(ctx context.Context, req *pb.CreateProductRequest) (*pb.CreateProductResponse, error) {
	rv, err := s.UB.CreateProduct(ctx, req)

	if err != nil {
		return nil, err
	}
	return rv, nil

}

func (s *ProductService) ListAllProducts(ctx context.Context, req *emptypb.Empty) (*pb.ListAllProductsResponse, error) {
	rv, err := s.UB.ListAllProducts(ctx, req)
	if err != nil {
		return nil, err
	}
	return rv, nil

}

func (s *ProductService) GetProducts(empt *emptypb.Empty, stream pb.ProductService_GetProductsServer) error {
	// slog.Info("called")
	err := s.UB.GetProducts(empt, stream)
	if err != nil {
		return err
	}

	return nil
}
