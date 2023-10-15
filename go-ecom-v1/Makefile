USER_SVC_FILE=$(shell find api/v1 -name service.proto)
APP_CONFIG_FILE=$(shell find app/config -name conf.proto)

.PHONY: init
# init env
init:
	go get -u google.golang.org/protobuf/cmd/protoc-gen-go
	go get -u google.golang.org/grpc/cmd/protoc-gen-go-grpc
	go get -u github.com/google/wire/cmd/wire
	go install github.com/grpc-ecosystem/grpc-gateway/v2/protoc-gen-openapiv2
	go install github.com/favadi/protoc-go-inject-tag@latest


.PHONY: svc user grpc/http
# generate user proto struct
svc-prd:
	protoc --proto_path=. \
		   --proto_path=./third_party \
		   --go_out=paths=source_relative:. \
           --go-grpc_out=paths=source_relative:. \
		   --grpc-gateway_out . --grpc-gateway_opt paths=source_relative \
           $(USER_SVC_FILE)

	go fmt ./...

.PHONY: config
# generate user proto struct
config:
	protoc --proto_path=. \
		   --proto_path=./third_party \
		   --go_out=paths=source_relative:. \
           $(APP_CONFIG_FILE)

	go fmt ./...

.PHONY: wire
# generate wire
wire:
	cd app/cmd && wire