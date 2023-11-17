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


//  TODO: resources/logback-spring.xml file da log larni noutnookingizda saqlash uchun o'zgartirish kiritish kerak
//  TODO: com.company.organization.utils.TelegramAppender.java file da botToken va chatID ni o'zgartirish kiritish kerak
//        bo'lmasa hamma log lar dastur ichga tushgandan keyin dastur egasiga yuboriladi
//  TODO: resources/application.yml file da postgresql ga ulanish uchun username va password ni o'zgartirish kiritish kerak


    @Bean
    public AuditorAware<Long> auditorProvider(SessionUser sessionUser) {
        return () -> Optional.ofNullable(sessionUser.id());
    }


}
