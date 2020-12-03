package com.five.monkey.mongo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jim
 * @date 2020/12/3 16:25
 */
@Configuration
public class MongoConfig {

    /**
     * Mongo server host. Cannot be set with URI.
     */
    @Value("${spring.data.mongodb.host}")
    private String host;

    /**
     * Mongo server port. Cannot be set with URI.
     */
    @Value("${spring.data.mongodb.port:27017}")
    private Integer port;

    /**
     * Database name.
     */
    @Value("${spring.data.mongodb.database}")
    private String database;

    /**
     * Authentication database name.
     */
    @Value("${spring.data.mongodb.authentication-database:admin}")
    private String authenticationDatabase;

    /**
     * Login user of the mongo server. Cannot be set with URI.
     */
    @Value("${spring.data.mongodb.username}")
    private String username;

    /**
     * Login password of the mongo server. Cannot be set with URI.
     */
    @Value("${spring.data.mongodb.password}")
    private String password;

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoConverter converter) {
        return new MongoTemplate(mongoDbFactory, converter);
    }

    @Bean
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoDbFactory(mongoClient, database);
    }

    @Bean
    public MongoClient mongoClient() {
        ServerAddress serverAddress = new ServerAddress(host, port);
        MongoCredential mongoCredential = MongoCredential.createCredential(username, authenticationDatabase, password.toCharArray());
        MongoClientOptions mongoClientOptions = new MongoClientOptions.Builder().build();
        return new MongoClient(serverAddress, mongoCredential, mongoClientOptions);
    }

    @Bean
    public MongoConverter mongoConverter(MongoDbFactory mongoDbFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext = new MongoMappingContext();
        return new MappingMongoConverter(dbRefResolver, mappingContext);
    }
}
