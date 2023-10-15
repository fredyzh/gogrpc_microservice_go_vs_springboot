package v1

import (
	"strconv"

	conf "github.com/fredyzh/ecom/product/app/config/v1"
	bus "github.com/fredyzh/ecom/product/app/internal/business/v1"
	"github.com/fredyzh/ecom/product/app/internal/data/mongo"
	svc "github.com/fredyzh/ecom/product/app/internal/service/v1"
	"github.com/google/wire"
	mgd "go.mongodb.org/mongo-driver/mongo"
)

var ProviderSet = wire.NewSet(NewHTTPServer, NewGRPCServer, NewDBInterface)

func NewDBInterface(cb *conf.Data_Database, s *svc.ProductService) *bus.ProductBusiness {
	mongodb := &mongo.MongoDB{
		Host:      cb.Host,
		Port:      strconv.Itoa(int(cb.Port)),
		DefualtDb: cb.Defaultdb,
		UserDB:    cb.CollUser,
		Timeout:   cb.Timeout.AsDuration(),
	}

	mongodb.DBClient = mongodb.ConnectDB().(*mgd.Client)

	s.UB = bus.ProductUseCase{
		DB: mongodb,
	}

	return &s.UB
}
