package go_test

import (
	"context"
	"io"
	"log"
	"testing"
	"time"

	pb "github.com/fredyzh/ecom/product/api/v1/proto"
	"github.com/stretchr/testify/require"

	grpc "google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
	"google.golang.org/protobuf/types/known/emptypb"
)

var addr string = "localhost:16777"

var (
	InitialCap = 5
	MaxIdleCap = 10
	MaximumCap = 100
	network    = "tcp"
	address    = "localhost:16777"

	factory = func() (interface{}, error) {
		opts := []grpc.DialOption{grpc.WithTransportCredentials(insecure.NewCredentials())}
		maxSizeOption := grpc.WithDefaultCallOptions(grpc.MaxCallRecvMsgSize(20*1024*1024),
			grpc.MaxCallSendMsgSize(20*1024*1024))
		opts = append(opts, maxSizeOption)
		return grpc.Dial(address, opts...)
	}
	closeFac = func(v interface{}) error {
		nc := v.(*grpc.ClientConn)
		return nc.Close()
	}
)

func TestGRPCServerLists(t *testing.T) {
	should := require.New(t)
	opts := []grpc.DialOption{grpc.WithTransportCredentials(insecure.NewCredentials())}
	maxSizeOption := grpc.WithDefaultCallOptions(grpc.MaxCallRecvMsgSize(20*1024*1024),
		grpc.MaxCallSendMsgSize(20*1024*1024))
	opts = append(opts, maxSizeOption)
	conn, err := grpc.Dial(addr, opts...)
	defer conn.Close()

	if err != nil {
		t.Error(err)
		t.Failed()
	}

	clt := pb.NewProductServiceClient(conn)

	prds, err := clt.ListAllProducts(context.Background(), &emptypb.Empty{})
	if err != nil {
		t.Error(err)
		t.Failed()
	}

	should.NoError(err)
	should.True(len(prds.GetProducts()) > 0)
}

func TestGRPCGetProducts(t *testing.T) {
	should := require.New(t)
	opts := []grpc.DialOption{grpc.WithTransportCredentials(insecure.NewCredentials())}

	conn, err := grpc.Dial(addr, opts...)
	defer conn.Close()

	if err != nil {
		t.Error(err)
		t.Failed()
	}

	clt := pb.NewProductServiceClient(conn)

	strm, err := clt.GetProducts(context.Background(), &emptypb.Empty{})
	if err != nil {
		t.Error(err)
		t.Failed()
	}

	products := []*pb.Product{}

	for {
		res, err := strm.Recv()

		if err == io.EOF {
			break
		}

		if err != nil {
			log.Fatalf("Something happened: %v\n", err)
		}

		products = append(products, res)
	}

	should.NoError(err)
	should.True(len(products) > 0)
}

func TestGRPCBatchGetProducts(t *testing.T) {
	should := require.New(t)

	p, err := newChannelPool()
	if err != nil {
		t.Error(err)
		t.Failed()
	}

	//done := make(chan bool)
	blk := make(chan int)
	//func(d chan bool, b chan bool) {
	total := 500
	count := 0
	for i := 0; i < total; i++ {
		conn, err := p.Get()
		if err != nil {
			t.Errorf("Get error: %s", err)
			t.Failed()
		}

		go func(c *grpc.ClientConn, cnt int, b chan int) {
			clt := pb.NewProductServiceClient(c)
			ctx, cancel := context.WithTimeout(context.Background(), 300*time.Second)
			defer cancel()

			strm, err := clt.GetProducts(ctx, &emptypb.Empty{})
			if err != nil {
				t.Error(err)
				t.Failed()
			}

			products := []*pb.Product{}

			for {
				res, err := strm.Recv()

				if err == io.EOF {
					p.Put(conn)
					blk <- cnt
					break
				}

				if err != nil {
					log.Fatalf("Something happened: %v\n", err)
				}

				products = append(products, res)
			}
		}(conn.(*grpc.ClientConn), i, blk)
	}

	for {
		c := <-blk
		// log.Println(c)
		count++
		if count == total-1 {
			log.Println(c)
			log.Println("#######")
			log.Println(count)
			break
		}
	}

	should.True(true)
}

func newChannelPool() (Pool, error) {
	pconf := Config{InitialCap: InitialCap, MaxCap: MaximumCap, Factory: factory, Close: closeFac, IdleTimeout: time.Second * 20,
		MaxIdle: MaxIdleCap}
	return NewChannelPool(&pconf)
}
