package main

import (
	"context"
	"flag"
	"log/slog"
	"os"
	"os/signal"
	"syscall"
	"time"

	conf "github.com/fredyzh/ecom/product/app/config/v1"
	bus "github.com/fredyzh/ecom/product/app/internal/business/v1"
	svc "github.com/fredyzh/ecom/product/app/internal/service/v1"

	"github.com/grpc-ecosystem/grpc-gateway/v2/runtime"

	"google.golang.org/grpc"
	"google.golang.org/protobuf/encoding/protojson"
	"sigs.k8s.io/yaml"
)

var (
	// Name is the name of the compiled software.
	Name = "user.Manager"
	// Version is the version of the compiled software.
	Version string
	// flagconf is the config flag.
	flagconf string
)

func init() {
	flag.StringVar(&flagconf, "conf", "./app/config/config.yaml", "config path, eg: -conf config.yaml")
}

func newApp(hs *runtime.ServeMux, gs *grpc.Server, cg *conf.Server_GRPC, ch *conf.Server_HTTP, pb *bus.ProductBusiness) *App {
	return &App{
		grpcServer:    gs,
		grpcConfig:    cg,
		httpServerMux: hs,
		httpConfig:    ch,
		prdBus:        pb,
	}
}

func main() {
	flag.Parse()

	yamlFile, err := os.ReadFile(flagconf)
	if err != nil {
		slog.Error("Error reading YAML file: %s\n", err)
		return
	}

	jsn, err := yaml.YAMLToJSON(yamlFile)
	if err != nil {
		slog.Error("err yaml to json: %v\n", err)
		return
	}

	var bc conf.Bootstrap = conf.Bootstrap{}
	err = protojson.UnmarshalOptions{DiscardUnknown: true}.Unmarshal(jsn, &bc)
	if err != nil {
		slog.Error("err parse json: %v\n", err)
		return
	}

	level := bc.Trace.Level
	programLevel := new(slog.LevelVar) // Info by default

	switch level {
	case conf.Loglevel_Debug:
		programLevel.Set(slog.LevelDebug)
	case conf.Loglevel_Error:
		programLevel.Set(slog.LevelError)
	case conf.Loglevel_Warn:
		programLevel.Set(slog.LevelWarn)
	}

	h := slog.NewTextHandler(os.Stderr, &slog.HandlerOptions{Level: programLevel})
	slog.SetDefault(slog.New(h))

	app, err := initApp(bc.Server.Grpc, bc.Server.Http, bc.Data.Database, &svc.ProductService{})
	if err != nil {
		slog.Error("init app failed: %v\n", err)
		return
	}

	go app.StartHttp(app.httpConfig.Port)
	go app.StartGrpc(app.grpcConfig.Port)

	sigChan := make(chan os.Signal, 1)
	signal.Notify(sigChan, syscall.SIGINT, syscall.SIGTERM)
	<-sigChan

	_, shutdownRelease := context.WithTimeout(context.Background(), 10*time.Second)
	defer shutdownRelease()

	app.grpcServer.GracefulStop()

	slog.Info("Graceful shutdown complete.")
}
