package com.fredyzh.ecom.product.v1;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

import java.net.http.HttpClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ClientBaseTests {
    String baseurl="localhost";
    int port=16777;

    String httpurl="http://localhost:8181/";

    ExecutorService executorService = Executors.newFixedThreadPool(100);

    ManagedChannel channel = NettyChannelBuilder.forAddress(baseurl, port).maxInboundMessageSize(20*1024*1024).executor(executorService).usePlaintext().build();

    GrpcClient grpcClient=new GrpcClient(channel);


    HttpClient client=HttpClient.newHttpClient();
}
