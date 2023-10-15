package mongo

import (
	"context"
	"fmt"
	"log/slog"
	"time"

	pb "github.com/fredyzh/ecom/product/api/v1/proto"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

type MongoDB struct {
	Host      string
	Port      string
	DefualtDb string
	UserDB    string
	DBClient  *mongo.Client
	Timeout   time.Duration
}

type MongoProduct struct {
	ID          primitive.ObjectID `bson:"_id" json:"id"`
	Name        string             `bson:"name" json:"name"`
	Description string             `bson:"description" json:"description"`
	Price       float64            `bson:"price" json:"price"`
	CreatedAt   primitive.DateTime `bson:"createdAt"`
	UpdatedAt   primitive.DateTime `bson:"updatedAt"`
}

func (m *MongoDB) ConnectDB() interface{} {
	mongoDbUri := fmt.Sprintf("mongodb://%s:%s", m.Host, m.Port)

	c, err := mongo.Connect(context.Background(), options.Client().ApplyURI(mongoDbUri))
	if err != nil {
		slog.Error(err.Error())
	}

	slog.Info("connected to MongoDB")
	return c
}

func (m *MongoDB) CreateProduct(ctx context.Context, prod *pb.Product) (interface{}, error) {
	coll := getCollection(m, m.UserDB)
	c, cancel := context.WithTimeout(ctx, m.Timeout)
	defer cancel()

	r, err := coll.InsertOne(c, getMongoProduct(prod))
	if err != nil {
		slog.Error(err.Error())
		return nil, err
	}

	return r.InsertedID, nil
}

func (m *MongoDB) ListAllProducts(ctx context.Context) ([]*pb.Product, error) {
	coll := getCollection(m, m.UserDB)
	c, cancel := context.WithTimeout(ctx, m.Timeout)
	defer cancel()

	csr, err := coll.Find(c, bson.M{})
	if err != nil {
		slog.Error(err.Error())
		return nil, err
	}
	defer csr.Close(ctx)
	var product []*pb.Product = []*pb.Product{}

	for csr.Next(ctx) {
		mprod := MongoProduct{}
		if err = csr.Decode(&mprod); err != nil {
			slog.Error(err.Error())
			return nil, err
		}

		product = append(product, &pb.Product{
			Id:          mprod.ID.String(),
			Name:        mprod.Name,
			Description: mprod.Description,
			Price:       mprod.Price,
		})
	}

	return product, nil
}

func (m *MongoDB) GetProducts(ctx context.Context, stream pb.ProductService_GetProductsServer) error {
	coll := getCollection(m, m.UserDB)
	c, cancel := context.WithTimeout(ctx, m.Timeout)
	defer cancel()

	csr, err := coll.Find(c, bson.M{})
	if err != nil {
		slog.Error(err.Error())
		return err
	}
	defer csr.Close(ctx)

	for csr.Next(ctx) {
		mprod := MongoProduct{}
		if err = csr.Decode(&mprod); err != nil {
			slog.Error(err.Error())
			return err
		}

		stream.Send(&pb.Product{
			Id:          mprod.ID.String(),
			Name:        mprod.Name,
			Description: mprod.Description,
			Price:       mprod.Price,
		})
	}
	stream.Context().Done()

	return nil
}

// common func
func getMongoProduct(prd *pb.Product) *MongoProduct {
	t := primitive.NewDateTimeFromTime(time.Now().AddDate(-1, 0, 0))
	return &MongoProduct{
		ID:          primitive.NewObjectID(),
		Name:        prd.Name,
		Description: prd.Description,
		Price:       prd.Price,
		CreatedAt:   t,
		UpdatedAt:   t,
	}
}

func getCollection(m *MongoDB, coll string) *mongo.Collection {
	client := m.DBClient

	if client == nil {
		m.DBClient = m.ConnectDB().(*mongo.Client)
		client = m.DBClient
	}

	return client.Database(m.DefualtDb).Collection(coll)
}
