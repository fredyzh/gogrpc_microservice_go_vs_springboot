package com.fredyzh.ecom.product.v1.app.server;

import com.fredyzh.ecom.product.v1.api.proto.CreateProductRequest;
import com.fredyzh.ecom.product.v1.api.proto.CreateProductResponse;
import com.fredyzh.ecom.product.v1.api.proto.ListAllProductsResponse;
import com.fredyzh.ecom.product.v1.api.proto.Product;
import com.fredyzh.ecom.product.v1.app.service.ProductService;
import com.google.gson.Gson;
import com.google.protobuf.Empty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class HttpServer {

    private final ProductService productService;

    @GetMapping(path = "/v1/health", produces ={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROTOBUF_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROTOBUF_VALUE})
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(productService.health(Empty.newBuilder().build()));
    }

    @PostMapping(path = "/v1/createproduct", produces ={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROTOBUF_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROTOBUF_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public  String createProduct(@RequestBody CreateProductRequest request){
        CreateProductResponse res= productService.createProduct(request);
        return "product "+res.getId()+" created.";
    }

    @GetMapping(path = "/v1/listallproducts", produces ={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROTOBUF_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROTOBUF_VALUE})
    public List<Product> listAllProducts(){
        ListAllProductsResponse res=productService.listAllProducts(Empty.newBuilder().build());
        return  res.getProductsList();
    }
}
