package com.example.notification.service.strategy;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending EMAIL to " + recipient + ": " + message);
    }
}
