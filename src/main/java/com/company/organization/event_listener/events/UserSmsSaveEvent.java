package com.company.organization.event_listener.events;

import com.company.organization.domain.user.User;
import com.company.organization.enums.SmsCodeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class UserSmsSaveEvent {
    private final User user;
    private final SmsCodeType smsCodeType;
}
