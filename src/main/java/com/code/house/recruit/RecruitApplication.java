package com.code.house.recruit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.code.house.recruit.common.nosql.doc"})
@EnableMongoRepositories(basePackages = {"com.code.house.recruit.common.nosql.repos"})
public class RecruitApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(RecruitApplication.class)
                .run(args);
    }
}

