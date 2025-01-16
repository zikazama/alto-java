package com.example.notification.config;

import com.example.notification.service.strategy.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationStrategyConfig {

    @Bean
    public NotificationStrategy emailNotificationStrategy() {
        return new EmailNotificationStrategy();
    }

    @Bean
    public NotificationStrategy smsNotificationStrategy() {
        return new SmsNotificationStrategy();
    }

    @Bean
    public NotificationStrategy pushNotificationStrategy() {
        return new PushNotificationStrategy();
    }
}
