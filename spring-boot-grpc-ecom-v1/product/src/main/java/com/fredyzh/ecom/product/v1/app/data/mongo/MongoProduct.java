package com.fredyzh.ecom.product.v1.app.data.mongo;

import com.fredyzh.ecom.product.v1.api.proto.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MongoProduct {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
