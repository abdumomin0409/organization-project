package com.company.organization.scheduler;

import com.company.organization.service.auth.AuthService;
import com.company.organization.service.auth.UserSmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class SchedulerConfig {

    @Value("+998930815351")
    private String superAdmin1;

    private final AuthService authService;
    private final UserSmsService userSmsService;

    @Async
    @Scheduled(fixedDelay = 60, timeUnit = TimeUnit.SECONDS)
    public void updateSMSWithTime() {
        userSmsService.updatedExpired();
    }

    @Async
    @Scheduled(initialDelay = 2, fixedDelay = 15 * 24 * 60, timeUnit = TimeUnit.MINUTES)
    public void insertSuperAdmins() {
        authService.updateSuperAdmin(superAdmin1);
    }


}
