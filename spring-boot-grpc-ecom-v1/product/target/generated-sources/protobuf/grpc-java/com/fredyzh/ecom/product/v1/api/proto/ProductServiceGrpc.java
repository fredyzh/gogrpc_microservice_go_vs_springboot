package com.fredyzh.ecom.product.v1.api.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProductServiceGrpc {

  private ProductServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "fredyzh.ecom.product.v1.ProductService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.fredyzh.ecom.product.v1.api.proto.HealthReply> getHealthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Health",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.fredyzh.ecom.product.v1.api.proto.HealthReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.fredyzh.ecom.product.v1.api.proto.HealthReply> getHealthMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.fredyzh.ecom.product.v1.api.proto.HealthReply> getHealthMethod;
    if ((getHealthMethod = ProductServiceGrpc.getHealthMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getHealthMethod = ProductServiceGrpc.getHealthMethod) == null) {
          ProductServiceGrpc.getHealthMethod = getHealthMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.fredyzh.ecom.product.v1.api.proto.HealthReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Health"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredyzh.ecom.product.v1.api.proto.HealthReply.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("Health"))
              .build();
        }
      }
    }
    return getHealthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest,
      com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse> getCreateProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateProduct",
      requestType = com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest.class,
      responseType = com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest,
      com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse> getCreateProductMethod() {
    io.grpc.MethodDescriptor<com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest, com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse> getCreateProductMethod;
    if ((getCreateProductMethod = ProductServiceGrpc.getCreateProductMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getCreateProductMethod = ProductServiceGrpc.getCreateProductMethod) == null) {
          ProductServiceGrpc.getCreateProductMethod = getCreateProductMethod =
              io.grpc.MethodDescriptor.<com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest, com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("CreateProduct"))
              .build();
        }
      }
    }
    return getCreateProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse> getListAllProductsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListAllProducts",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse> getListAllProductsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse> getListAllProductsMethod;
    if ((getListAllProductsMethod = ProductServiceGrpc.getListAllProductsMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getListAllProductsMethod = ProductServiceGrpc.getListAllProductsMethod) == null) {
          ProductServiceGrpc.getListAllProductsMethod = getListAllProductsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListAllProducts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("ListAllProducts"))
              .build();
        }
      }
    }
    return getListAllProductsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.fredyzh.ecom.product.v1.api.proto.Product> getGetProductsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getProducts",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.fredyzh.ecom.product.v1.api.proto.Product.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.fredyzh.ecom.product.v1.api.proto.Product> getGetProductsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.fredyzh.ecom.product.v1.api.proto.Product> getGetProductsMethod;
    if ((getGetProductsMethod = ProductServiceGrpc.getGetProductsMethod) == null) {
      synchronized (ProductServiceGrpc.class) {
        if ((getGetProductsMethod = ProductServiceGrpc.getGetProductsMethod) == null) {
          ProductServiceGrpc.getGetProductsMethod = getGetProductsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.fredyzh.ecom.product.v1.api.proto.Product>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getProducts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredyzh.ecom.product.v1.api.proto.Product.getDefaultInstance()))
              .setSchemaDescriptor(new ProductServiceMethodDescriptorSupplier("getProducts"))
              .build();
        }
      }
    }
    return getGetProductsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProductServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductServiceStub>() {
        @java.lang.Override
        public ProductServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductServiceStub(channel, callOptions);
        }
      };
    return ProductServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProductServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductServiceBlockingStub>() {
        @java.lang.Override
        public ProductServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductServiceBlockingStub(channel, callOptions);
        }
      };
    return ProductServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProductServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductServiceFutureStub>() {
        @java.lang.Override
        public ProductServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductServiceFutureStub(channel, callOptions);
        }
      };
    return ProductServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void health(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.HealthReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHealthMethod(), responseObserver);
    }

    /**
     */
    default void createProduct(com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest request,
        io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateProductMethod(), responseObserver);
    }

    /**
     */
    default void listAllProducts(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListAllProductsMethod(), responseObserver);
    }

    /**
     */
    default void getProducts(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.Product> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetProductsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ProductService.
   */
  public static abstract class ProductServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProductServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ProductService.
   */
  public static final class ProductServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ProductServiceStub> {
    private ProductServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductServiceStub(channel, callOptions);
    }

    /**
     */
    public void health(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.HealthReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHealthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createProduct(com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest request,
        io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listAllProducts(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListAllProductsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProducts(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.Product> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetProductsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ProductService.
   */
  public static final class ProductServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProductServiceBlockingStub> {
    private ProductServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.fredyzh.ecom.product.v1.api.proto.HealthReply health(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHealthMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse createProduct(com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse listAllProducts(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListAllProductsMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.fredyzh.ecom.product.v1.api.proto.Product> getProducts(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetProductsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ProductService.
   */
  public static final class ProductServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProductServiceFutureStub> {
    private ProductServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.fredyzh.ecom.product.v1.api.proto.HealthReply> health(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHealthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse> createProduct(
        com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse> listAllProducts(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListAllProductsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HEALTH = 0;
  private static final int METHODID_CREATE_PRODUCT = 1;
  private static final int METHODID_LIST_ALL_PRODUCTS = 2;
  private static final int METHODID_GET_PRODUCTS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HEALTH:
          serviceImpl.health((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.HealthReply>) responseObserver);
          break;
        case METHODID_CREATE_PRODUCT:
          serviceImpl.createProduct((com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest) request,
              (io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse>) responseObserver);
          break;
        case METHODID_LIST_ALL_PRODUCTS:
          serviceImpl.listAllProducts((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse>) responseObserver);
          break;
        case METHODID_GET_PRODUCTS:
          serviceImpl.getProducts((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.fredyzh.ecom.product.v1.api.proto.Product>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getHealthMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.fredyzh.ecom.product.v1.api.proto.HealthReply>(
                service, METHODID_HEALTH)))
        .addMethod(
          getCreateProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest,
              com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse>(
                service, METHODID_CREATE_PRODUCT)))
        .addMethod(
          getListAllProductsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse>(
                service, METHODID_LIST_ALL_PRODUCTS)))
        .addMethod(
          getGetProductsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.fredyzh.ecom.product.v1.api.proto.Product>(
                service, METHODID_GET_PRODUCTS)))
        .build();
  }

  private static abstract class ProductServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProductServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.fredyzh.ecom.product.v1.api.proto.GRPCProductService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProductService");
    }
  }

  private static final class ProductServiceFileDescriptorSupplier
      extends ProductServiceBaseDescriptorSupplier {
    ProductServiceFileDescriptorSupplier() {}
  }

  private static final class ProductServiceMethodDescriptorSupplier
      extends ProductServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProductServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProductServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProductServiceFileDescriptorSupplier())
              .addMethod(getHealthMethod())
              .addMethod(getCreateProductMethod())
              .addMethod(getListAllProductsMethod())
              .addMethod(getGetProductsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
