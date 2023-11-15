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
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIrOTk4OTMwODE1MzUxIiwiaWF0IjoxNzAwMDM5MDI2LCJpc3MiOiIgICAiLCJleHAiOjE3MDAwNDYyMjZ9.TgJi3MCTb57YN5wsGs46eQH5puwAHVMXx0T3rh5_-QSgBM_7Dxt57009Wo5K4X12gj40COAEirk8XZVopTQkkA",
    "accessTokenExpiry": "2023-11-15T11:03:46.788+00:00",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk4OTMwODE1MzUxIiwiaWF0IjoxNzAwMDM5MDI2LCJpc3MiOiIgICAiLCJleHAiOjE3MDA5MDMwMjZ9.i_bS-OITVO9vOOT9eRXFTdDa2iTa5LV28HoxciCqRSE",
    "refreshTokenExpiry": "2023-11-25T09:03:46.805+00:00"
  }

     */

}
