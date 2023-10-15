package com.fredyzh.ecom.product.v1.app.service;

import com.fredyzh.ecom.product.v1.api.proto.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public interface ProductService {
    HealthReply health(Empty request);

    ListAllProductsResponse listAllProducts(Empty request);

    CreateProductResponse createProduct(CreateProductRequest request);

    void getProducts(StreamObserver<Product> responseObserver);
}
