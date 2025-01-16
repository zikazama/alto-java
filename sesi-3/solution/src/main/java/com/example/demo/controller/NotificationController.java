package com.example.notification.controller;

import com.example.notification.model.NotificationRequest;
import com.example.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send-notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public String sendNotification(@RequestBody NotificationRequest request) {
        notificationService.sendNotification(request);
        return "Notification sent successfully!";
    }
}
