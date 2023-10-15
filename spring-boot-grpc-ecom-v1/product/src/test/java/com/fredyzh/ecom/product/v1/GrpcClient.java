package com.fredyzh.ecom.product.v1;

import com.fredyzh.ecom.product.v1.api.proto.ProductServiceGrpc;
import com.fredyzh.ecom.product.v1.api.proto.ProductServiceGrpc.ProductServiceBlockingStub;
import com.fredyzh.ecom.product.v1.api.proto.ProductServiceGrpc.ProductServiceFutureStub;
import com.fredyzh.ecom.product.v1.api.proto.ProductServiceGrpc.ProductServiceStub;
import io.grpc.Channel;


public class GrpcClient {
    private static ProductServiceBlockingStub prdBlkStub;
    private static ProductServiceFutureStub prdFutureStub;

    private static ProductServiceStub prdStub;

    public GrpcClient(Channel chan) {
        prdBlkStub=ProductServiceGrpc.newBlockingStub(chan);
        prdFutureStub=ProductServiceGrpc.newFutureStub(chan);
        prdStub=ProductServiceGrpc.newStub(chan);
    }


    public ProductServiceBlockingStub getPrdBlkStub() {
        return prdBlkStub;
    }

    public ProductServiceFutureStub getPrdFutureStub() {
        return prdFutureStub;
    }

    public ProductServiceStub getPrdStub() {
        return prdStub;
    }
}
