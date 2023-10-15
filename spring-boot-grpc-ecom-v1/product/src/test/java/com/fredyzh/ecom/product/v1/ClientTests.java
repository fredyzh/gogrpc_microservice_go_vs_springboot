package com.fredyzh.ecom.product.v1;

import com.fredyzh.ecom.product.v1.api.proto.HealthReply;
import com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse;
import com.fredyzh.ecom.product.v1.api.proto.Product;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientTests extends ClientBaseTests{
    private Runnable testGRPCAsyncListRunnable = () -> {
        TestGRPCAsyncListClient();
    };

    private Runnable testGRPCAsyncPordsRunnable = () -> {
        TestGRPCAsyncGetProdsClient();
    };

   @Test
    void TestGRPCClient(){
        HealthReply rep=grpcClient.getPrdBlkStub().health(Empty.newBuilder().build());
        assert (rep.getOk());
    }

    @Test
    void TestGRPCFutureClient() throws ExecutionException, InterruptedException {
        ListenableFuture<HealthReply> rep=grpcClient.getPrdFutureStub().health(Empty.newBuilder().build());
        assert (rep.get().getOk());
    }

    @Test
    void TestGRPCListClient(){
        ListAllProductsResponse rep=grpcClient.getPrdBlkStub().listAllProducts(Empty.newBuilder().build());
        assert (!rep.getProductsList().isEmpty());
    }

    @Test
    void TestGRPCAsyncGetProdsClient(){
        final CountDownLatch finishLatch = new CountDownLatch(1);
        AtomicBoolean isError=new AtomicBoolean(false);

        var responseObserver = new StreamObserver<Product>() {
           List<Product> prods=new CopyOnWriteArrayList<Product>();

            @Override
            public void onNext(Product value) {
                prods.add(value);
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                isError.getAndSet(true);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
            }

            public List<Product> getResp(){
                return prods;
            }
        };

        grpcClient.getPrdStub().getProducts(Empty.newBuilder().build(), responseObserver);

        try {
            finishLatch.await(5, TimeUnit.SECONDS);

            if (isError.compareAndSet(true, false)) {
                throw new RuntimeException("failed retrieved history data.");
            }
        } catch (InterruptedException e) {
        }
        finally {
            channel.isShutdown();
        }
        var rest=responseObserver.getResp();
        assert (!rest.isEmpty());
    }
    @Test
    void TestGRPCAsyncListClient(){
        final CountDownLatch finishLatch = new CountDownLatch(1);
        AtomicBoolean isError=new AtomicBoolean(false);

        var responseObserver = new StreamObserver<ListAllProductsResponse>() {
            ListAllProductsResponse resp;

            @Override
            public void onNext(ListAllProductsResponse value) {
                resp=value;
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                isError.getAndSet(true);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
            }

            public ListAllProductsResponse getResp(){
                return resp;
            }
        };

        grpcClient.getPrdStub().listAllProducts(Empty.newBuilder().build(), responseObserver);

        try {
            finishLatch.await(15, TimeUnit.SECONDS);

            if (isError.compareAndSet(true, false)) {
                throw new RuntimeException("failed retrieved history data.");
            }
        } catch (InterruptedException e) {
        }
        finally {
            channel.isShutdown();
        }
        var rest=responseObserver.getResp();
       assert (!rest.getProductsList().isEmpty());
    }

    @Test
    void TestGRPCFutureListClient() throws ExecutionException, InterruptedException {
        ListenableFuture<ListAllProductsResponse> rep=grpcClient.getPrdFutureStub().listAllProducts(Empty.newBuilder().build());
        assert (!rep.get().getProductsList().isEmpty());
    }

    @Test
    void TestHttpClient() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(httpurl+"product/v1/health")).header("Content-Type", "application/json").GET().build();
        HttpResponse<String> rep = client.send(request, HttpResponse.BodyHandlers.ofString());

        assert (!rep.body().isEmpty());
        assert (rep.body().contains("ok"));
    }

    @Test
    void TestHttpListProdClient() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(httpurl+"product/v1/listallproducts")).header("Content-Type", "application/json").GET().build();
        HttpResponse<String> rep = client.send(request, HttpResponse.BodyHandlers.ofString());

        assert (!rep.body().isEmpty());
    }

    @Test
    void TestHttpAsyncClient() throws URISyntaxException, IOException, InterruptedException, ExecutionException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(httpurl+"product/v1/health")).header("Content-Type", "application/json").GET().build();
        CompletableFuture<String> rep= client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(response -> response.body());

        assert (!rep.get().isEmpty());
        assert (rep.get().contains("ok"));
//        Assertions.assertTrue(rep.body().contains("ok"));
    }
    @Test
    void TestBatchHttpClient() throws ExecutionException, InterruptedException, URISyntaxException, IOException {
        for (int i=0; i<1000; i++){
            TestHttpListProdClient();
        }
    }

    @Test
    void TestBatchHttpAsyncClient() throws ExecutionException, InterruptedException, URISyntaxException, IOException {
        for (int i=0; i<10000; i++){
            TestHttpAsyncClient();
        }
    }

    @Test
    void TestGRPCBatchFutureClient() throws ExecutionException, InterruptedException {
        for (int i=0; i<10000; i++){
//            TestGRPCFutureClient();
            TestGRPCFutureListClient();
        }
    }

    @Test
    void TestGRPCBatchBlkClient() throws ExecutionException, InterruptedException {
        for (int i=0; i<1000; i++){
            TestGRPCListClient();
        }
    }

    @Test
    void TestGRPCAsyncClient() throws ExecutionException, InterruptedException {
        for (int i=0; i<1000; i++){
            testGRPCAsyncListRunnable.run();
        }
    }

    @Test
    void TestGRPCAsyncGetProds() throws ExecutionException, InterruptedException {
        for (int i=0; i<500; i++){
            testGRPCAsyncPordsRunnable.run();
        }
    }
}
