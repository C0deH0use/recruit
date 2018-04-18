package com.code.house.recruit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = "com.code.house.recruit")
@EntityScan(basePackages = {"com.code.house.recruit.common.nosql.documents"})
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {"com.code.house.recruit.common.nosql.repos"})
public class RecruitApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(RecruitApplication.class)
                .run(args);
    }
}

