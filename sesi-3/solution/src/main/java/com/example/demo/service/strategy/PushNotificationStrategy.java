package com.example.notification.service.strategy;

import org.springframework.stereotype.Service;

@Service
public class PushNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending PUSH notification to " + recipient + ": " + message);
    }
}
