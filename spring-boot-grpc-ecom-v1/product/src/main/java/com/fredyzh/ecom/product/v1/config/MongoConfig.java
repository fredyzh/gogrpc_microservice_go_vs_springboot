package com.fredyzh.ecom.product.v1.config;

import com.google.gson.*;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Data
@RequiredArgsConstructor
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    private static final String DATE_FORMAT="yyyy-MM-dd'T'HH:mm:ss";

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Value("${spring.data.mongodb.connection-pool.max-size}")
    private int connPoolMaxSize;

    @Value("${spring.data.mongodb.connection-pool.max-wait-time}")
    private int connPoolMaxWaitTime;

    @Value("${spring.data.mongodb.connection-pool.max-connecting}")
    private int connPoolMaxConnecting;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Bean
    @Override
    public MongoClientSettings mongoClientSettings() {
        Block<ConnectionPoolSettings.Builder> block= new Block<ConnectionPoolSettings.Builder>() {
            @Override
            public void apply(ConnectionPoolSettings.Builder builder) {
                builder.maxSize(connPoolMaxSize).maxWaitTime(connPoolMaxWaitTime, TimeUnit.SECONDS).maxConnecting(connPoolMaxConnecting).build();
            }
        };

        return MongoClientSettings.builder().applyToConnectionPoolSettings(block).build();
    }

    @Bean
    public Gson getGson(){
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                var jo=json.toString();
                var start=jo.indexOf(":");
                var end =jo.indexOf(".");
                String dateStr=null;
                if (end>0){
                    dateStr=jo.substring(start+1, end).replace("\"", "").replace("}", "");
                }else {
                    dateStr=jo.substring(start+1).replace("\"", "").replace("}", "").replace("Z", "");
                }

                return LocalDateTime.parse(dateStr, DateTimeFormatter
                        .ofPattern(DATE_FORMAT, Locale.ENGLISH)); }
        }).create();
    }
}
