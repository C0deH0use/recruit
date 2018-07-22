package com.code.house.recruit.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EntityScan(basePackages = {"com.code.house.recruit.data.nosql.documents"})
@EnableMongoRepositories(basePackages = {"com.code.house.recruit.data.nosql.repos"})
public class DataConfig {
}
