package com.company.organization.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class TelegramAppender extends AppenderBase<LoggingEvent> {
    private static final String botToken = "5545958714:AAFzeHx-LA2OFejBM3gwWRIW5RYbPhM9Gmo";
    private static final String chatID = "1752907421";
    private static final TelegramBot telegramBot = new TelegramBot(botToken);

    public TelegramAppender() {
        addFilter(new Filter<>() {
            @Override
            public FilterReply decide(LoggingEvent loggingEvent) {
                return loggingEvent.getLevel().equals(Level.ERROR) ? FilterReply.ACCEPT : FilterReply.DENY;
            }
        });
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        String logMessage = loggingEvent.toString();
        SendMessage sendMessage = new SendMessage(chatID, logMessage);
        telegramBot.execute(sendMessage);
    }
}
