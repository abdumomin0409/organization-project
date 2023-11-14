package com.company.organization.event_listener.listeners;

import com.company.organization.domain.user.User;
import com.company.organization.enums.SmsCodeType;
import com.company.organization.event_listener.events.GoingTwilioEvent;
import com.company.organization.event_listener.events.UserSmsSaveEvent;
import com.company.organization.service.auth.TwilioService;
import com.company.organization.service.auth.UserSmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSmsListener {

    private final UserSmsService userSmsService;
    private final TwilioService twilioService;

    @Async
    @EventListener(value = UserSmsSaveEvent.class)
    public void userSmsSaveEventListener(UserSmsSaveEvent event) {
        User user = event.getUser();
        SmsCodeType smsCodeType = event.getSmsCodeType();
        if (user != null) {
            userSmsService.createUserSms(user, smsCodeType);
        }
    }

    @Async
    @EventListener(value = GoingTwilioEvent.class)
    public void goingTwilioEventListener(GoingTwilioEvent event) {
        String toPhoneNumber = event.getToPhoneNumber();
        Integer randomCode = event.getRandomCode();
        if (toPhoneNumber != null && randomCode != null) {
            System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");
            System.out.println(" = = = =                                                         = = = ");
            System.out.println(" = = = =     " + randomCode + "                                          = = = ");
            System.out.println(" = = = =                                                         = = = ");
            System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");
//            twilioService.sendSMSCode(toPhoneNumber, randomCode);
        }
    }

}
