package com.company.organization.event_listener.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class GoingTwilioEvent {
    private final String toPhoneNumber;
    private final Integer randomCode;
}
