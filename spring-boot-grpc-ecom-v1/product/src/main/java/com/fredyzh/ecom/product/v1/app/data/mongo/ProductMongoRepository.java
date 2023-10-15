package com.fredyzh.ecom.product.v1.app.data.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<MongoProduct, String> {
}
