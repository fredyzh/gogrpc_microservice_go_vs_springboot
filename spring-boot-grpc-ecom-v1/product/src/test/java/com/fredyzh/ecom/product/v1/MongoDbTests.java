package com.fredyzh.ecom.product.v1;

import com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest;
import com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse;
import com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse;
import com.fredyzh.ecom.product.v1.api.proto.Product;
import com.fredyzh.ecom.product.v1.app.data.mongo.MongoProduct;
import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class MongoDbTests extends ClientBaseTests{
    Product product=Product.newBuilder()
            .setName("prod")
            .setDescription("test prod")
            .setPrice(100.32).build();

    MongoProduct mongoProduct=MongoProduct.builder()
            .description(product.getDescription())
            .price(product.getPrice())
            .name(product.getName())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

    @Test
    void TestCreateProduct(){
        AtomicBoolean isError = createProd(product);
        assert (!isError.get());
    }

    @NotNull
    private AtomicBoolean createProd(Product prd) {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        AtomicBoolean isError=new AtomicBoolean(false);

        var responseObserver = new StreamObserver<CreateProductResponse>() {

            @Override
            public void onNext(CreateProductResponse value) {
//                System.out.println(value.getId());
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
        };

        grpcClient.getPrdStub().createProduct(CreateProductRequest.newBuilder().setProduct(prd).build(), responseObserver);

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
        return isError;
    }

    @Test
    void TestCreateProducts() throws InterruptedException {
        String des="big string and ";
        StringBuilder bd=new StringBuilder();
        for (int j=0;j<1000; j++){
            bd.append(des);
        }

        for (int i=0;i<1000; i++){
            Product prod=Product.newBuilder()
                    .setName("prod"+i)
                    .setDescription(bd.toString())
                    .setPrice(100.32).build();

            int finalI = i;
            Runnable testGRPCAsyncPordsRunnable = () -> {
                AtomicBoolean isError = createProd(prod);
//                System.out.println(String.valueOf(finalI));
                assert (!isError.get());
            };
            testGRPCAsyncPordsRunnable.run();
//            Thread.sleep(5000);
        }
    }
}
