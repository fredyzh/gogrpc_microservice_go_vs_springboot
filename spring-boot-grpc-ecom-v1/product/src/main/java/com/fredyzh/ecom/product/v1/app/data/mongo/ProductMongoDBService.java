package com.fredyzh.ecom.product.v1.app.data.mongo;

import com.fredyzh.ecom.product.v1.api.proto.Product;
import com.fredyzh.ecom.product.v1.app.data.DataRepository;
import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductMongoDBService implements DataRepository {
    private final static String MONGO_ID="_id";

    private final ProductMongoRepository productMongoRepository;

    private final MongoTemplate mt;

    private final Gson gson;

    @Value("${spring.data.mongodb.collection}")
    private String cloolecction;


    @Override
    public List<Product> listAllProducts() {
        List<MongoProduct> list=productMongoRepository.findAll();
        return list.stream().map(this::getPbProduct).toList();
    }

    @Override
    public String createProduct(Product product) {
        MongoProduct ret =productMongoRepository.save(getMongoProduct(product));
        return ret.getId();
    }

    @Override
    public void getProducts(StreamObserver<Product> responseObserver) {
        MongoCursor<Document> cursor =mt.getCollection(cloolecction).find().cursor();
        while (cursor.hasNext()){
            Document doc=cursor.next();
            MongoProduct mprd=gson.fromJson(doc.toJson(), MongoProduct.class);
            mprd.setId(doc.getObjectId(MONGO_ID).toHexString());

            responseObserver.onNext(getPbProduct(mprd));
        }

        responseObserver.onCompleted();
    }

    private MongoProduct getMongoProduct(Product prd){
        return MongoProduct.builder()
                .name(prd.getName())
                .description(prd.getDescription())
                .price(prd.getPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();
    }

    private Product getPbProduct(MongoProduct prd){
        return Product.newBuilder()
                .setId(prd.getId())
                .setName(prd.getName())
                .setDescription(prd.getDescription())
                .setPrice(prd.getPrice()).build();
    }
}
