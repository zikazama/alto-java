package com.example.notification.service.strategy;

public interface NotificationStrategy {
    void send(String recipient, String message);
}
