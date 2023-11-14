package com.company.organization.service.auth;

import com.company.organization.domain.user.User;
import com.company.organization.domain.user.UserSms;
import com.company.organization.enums.SmsCodeType;
import com.company.organization.event_listener.events.GoingTwilioEvent;
import com.company.organization.repository.auth.UserSmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserSmsService {
    private final UserSmsRepository userSmsRepository;
    private final Random random;
    private final TwilioService twilioService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void createUserSms(User savedUser, SmsCodeType type) {
        Long userId = savedUser.getId();
        UserSms userSms = findByUserId(userId, type);
        if (!Objects.isNull(userSms)) {
            return;
        }
        UserSms build = UserSms.builder()
                .userId(savedUser.getId())
//                .randomCode(randomCode())
                .randomCode(123456)
                .type(type)
                .toTime(LocalDateTime.now().plus(3, ChronoUnit.MINUTES))
                .build();
        build.setExpired(false);
        userSmsRepository.save(build);
        applicationEventPublisher.publishEvent(new GoingTwilioEvent(savedUser.getPhoneNumber(), build.getRandomCode()));
    }


    public UserSms findByUserId(Long userId, SmsCodeType type) {
        return userSmsRepository.findByUserId(userId, type);
    }

    private Integer randomCode() {
        return random.nextInt(999_999 - 100_000) + 100_000;
    }

    public void updatedExpired() {
        userSmsRepository.updateExpired();
    }

    public void update(UserSms userSms) {
        userSmsRepository.save(userSms);
    }
}
