package com.code.house.recruit.common.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EntityScan(basePackages = {"com.code.house.recruit.common.nosql.documents"})
@EnableReactiveMongoRepositories(basePackages = {"com.code.house.recruit.common.nosql.repos"})
public class TestMongoConfig extends AbstractReactiveMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Bean
    @Override
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

}
