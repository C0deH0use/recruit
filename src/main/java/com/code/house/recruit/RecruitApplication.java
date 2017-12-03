package com.code.house.recruit;

import com.code.house.recruit.common.config.MongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MongoConfig.class})
public class RecruitApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecruitApplication.class, args);
	}
}

