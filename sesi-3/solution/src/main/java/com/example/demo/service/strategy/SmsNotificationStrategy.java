package com.example.notification.service.strategy;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}
