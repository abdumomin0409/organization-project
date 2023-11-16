package com.company.organization;

import com.company.organization.security.SessionUser;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
@OpenAPIDefinition
@EnableJpaAuditing
public class OrganizationApplication {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "Asia/Tashkent");
        SpringApplication.run(OrganizationApplication.class, args);
    }


    @Bean
    public AuditorAware<Long> auditorProvider(SessionUser sessionUser) {
        return () -> Optional.ofNullable(sessionUser.id());
    }

    /*

  {
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIrOTk4OTMwODE1MzUxIiwiaWF0IjoxNzAwMTMxNzMyLCJpc3MiOiIgICAiLCJleHAiOjE3MDAxMzg5MzJ9.66E8xxnVGXPFELroQzrqNlG6Sf2oAAqOzsW4fQBU_NHkKY-0i6DtNmrngk9tUUNVTJp7jtbg4yJmIvuq8Y0CCw",
    "accessTokenExpiry": "2023-11-16T12:48:52.663+00:00",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk4OTMwODE1MzUxIiwiaWF0IjoxNzAwMTMxNzMyLCJpc3MiOiIgICAiLCJleHAiOjE3MDA5OTU3MzJ9.1r2SesgmBZwYLj-wzFJeJWAPkUeMx9pR2e-kJVMWyHE",
    "refreshTokenExpiry": "2023-11-26T10:48:52.677+00:00"
  }

     */

}
