package com.example.notification.service;

import com.example.notification.model.NotificationRequest;
import com.example.notification.service.strategy.NotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService {

    private final Map<String, NotificationStrategy> strategies;

    @Autowired
    public NotificationService(Map<String, NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    public void sendNotification(NotificationRequest request) {
        NotificationStrategy strategy = strategies.get(request.getType().toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported notification type: " + request.getType());
        }
        strategy.send(request.getRecipient(), request.getMessage());
    }
}
