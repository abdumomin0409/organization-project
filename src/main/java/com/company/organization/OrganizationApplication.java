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
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIrOTk4OTMwODE1MzUxIiwiaWF0IjoxNzAwMTA5NjI3LCJpc3MiOiIgICAiLCJleHAiOjE3MDAxMTY4Mjd9.B2Dt5WB06yK8OYFUNjphdjDNYAhsQvvpKSbUOOgimd16nYjmg7S4DLf-NXlhegCr9RKGZfc3yFrOlGy1DP3UDA",
    "accessTokenExpiry": "2023-11-16T06:40:27.239+00:00",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk4OTMwODE1MzUxIiwiaWF0IjoxNzAwMTA5NjI3LCJpc3MiOiIgICAiLCJleHAiOjE3MDA5NzM2Mjd9.mmkG4aufdC12-HCjs0RAlO5zECe1IFuIIe_iGhL7u38",
    "refreshTokenExpiry": "2023-11-26T04:40:27.264+00:00"
  }

     */

}
