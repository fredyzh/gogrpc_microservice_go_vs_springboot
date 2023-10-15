package com.fredyzh.ecom.product.v1.app.business;


import com.fredyzh.ecom.product.v1.api.proto.*;
import com.fredyzh.ecom.product.v1.app.data.DataRepository;
import com.fredyzh.ecom.product.v1.app.data.mongo.MongoProduct;
import com.fredyzh.ecom.product.v1.app.data.mongo.ProductMongoRepository;
import com.fredyzh.ecom.product.v1.app.service.ProductService;
import com.google.gson.Gson;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusinessService implements ProductService {

    private final DataRepository dataRepository;

    public HealthReply health(Empty request) {
        return HealthReply.newBuilder().setOk(true)
                .build();
    }

    @Override
    public ListAllProductsResponse listAllProducts(Empty request) {
        return  ListAllProductsResponse.newBuilder().addAllProducts(dataRepository.listAllProducts()).build();
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        String id=dataRepository.createProduct(request.getProduct());
        log.debug("Product {} is saved", id);
        return CreateProductResponse.newBuilder().setId(id).build();
    }

    @Override
    public void getProducts(StreamObserver<Product> responseObserver) {
        dataRepository.getProducts(responseObserver);
    }
}
