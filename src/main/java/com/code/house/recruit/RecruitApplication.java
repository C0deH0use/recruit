package com.code.house.recruit;

import com.code.house.recruit.common.config.MongoConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MongoConfig.class})
public class RecruitApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(RecruitApplication.class)
                .run(args);
    }
}

