package com.code.house.recruit;

import com.code.house.recruit.data.DataConfig;
import com.code.house.recruit.domain.ServiceDomainConfig;
import com.code.house.recruit.web.WebConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@SuppressWarnings("PMD.UseUtilityClass")
@Import(value = {WebConfig.class, ServiceDomainConfig.class, DataConfig.class})
public class RecruitApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(RecruitApplication.class)
                .run(args);
    }
}

