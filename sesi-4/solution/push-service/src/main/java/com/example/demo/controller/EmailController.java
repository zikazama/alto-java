package com.example.demo.controller;
import com.example.demo.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @PostMapping("/send")
    public String sendEmail(@RequestBody NotificationRequest request) {
        // Here you would typically integrate with an email service 
        // like JavaMailSender or a third-party library.
        // This example just simulates sending an email.

        // Replace with actual email sending logic
        if (sendEmailTo(request.getRecipient(), request.getMessage())) {
            return "Email sent to " + request.getRecipient() + ": " + request.getMessage();
        } else {
            return "Failed to send email to " + request.getRecipient();
        }
    }

    // Simulated email sending method (replace with actual implementation)
    private boolean sendEmailTo(String recipient, String message) {
        // Logic to send the email (e.g., using JavaMailSender)
        // ...
        return true; // Assuming email was sent successfully
    }
}