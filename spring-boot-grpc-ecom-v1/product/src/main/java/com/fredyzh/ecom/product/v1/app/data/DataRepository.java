package com.fredyzh.ecom.product.v1.app.data;

import com.fredyzh.ecom.product.v1.api.proto.Product;
import io.grpc.stub.StreamObserver;

import java.util.List;

public interface DataRepository {

    List<Product> listAllProducts();

    String createProduct(Product product);

    void getProducts(StreamObserver<Product> responseObserver);
}
