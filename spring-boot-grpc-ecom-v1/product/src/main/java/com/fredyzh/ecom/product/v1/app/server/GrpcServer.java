package com.fredyzh.ecom.product.v1.app.server;

import com.fredyzh.ecom.product.v1.api.proto.*;
import com.fredyzh.ecom.product.v1.api.proto.ProductServiceGrpc.ProductServiceImplBase;
import com.fredyzh.ecom.product.v1.app.service.ProductService;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class GrpcServer extends ProductServiceImplBase {

    private final ProductService productService;
    @Override
    public void health(Empty request, StreamObserver<HealthReply> responseObserver) {
        responseObserver.onNext(productService.health(request));
        responseObserver.onCompleted();
    }

    @Override
    public void createProduct(CreateProductRequest request, StreamObserver<CreateProductResponse> responseObserver) {
        responseObserver.onNext(productService.createProduct(request));
        responseObserver.onCompleted();
    }

    @Override
    public void listAllProducts(Empty request, StreamObserver<ListAllProductsResponse> responseObserver) {
        responseObserver.onNext(productService.listAllProducts(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getProducts(Empty request, StreamObserver<Product> responseObserver) {
        productService.getProducts(responseObserver);
    }
}
