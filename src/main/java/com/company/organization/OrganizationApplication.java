package com.company.organization;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
@OpenAPIDefinition
public class OrganizationApplication {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "Asia/Tashkent");
        SpringApplication.run(OrganizationApplication.class, args);
    }

}
