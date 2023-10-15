# grpc-microservice-go-springboot-comparison

## Overview
- same proto buffer file service.proto
- implemented by go and spring boot
- grpc objects as DTO
- same mongo database

## Go Implementation
```
.
├── api  
│   ├── v1
│   │   ├── proto //service.proto and generated artifacts
├── app  //
│   ├── cmd
│   └── config
│   |    └── v1
|   ├── internal
|   |       └── business
|   |       |       └── v1
|   |       |        
|   |       └── data
|   |       |       └── mongo
|   |       |── server
|   |               └── v1
|   |       |── service
|   |               └── v1
|
└── client
|       └── go
├── third_party
|       └── google
|               └── api

```

## Spring Boot Implementation
```
src/main/java/com/fredyzh/ecom/product/v1
    ├── api  
    │    └── proto //service.proto
    ├── app  //
    |    └── business       
    |    └── data
    |          └── mongo
    |    └── server
    |    └── service
    └── config

```