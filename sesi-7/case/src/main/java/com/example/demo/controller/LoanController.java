package com.example.demo.controller;

import com.example.demo.dto.LoanApplication;
import com.example.demo.dto.Notification;
import com.example.demo.kafka.KafkaProducerService;
import com.example.demo.rabbitmq.RabbitMQProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final KafkaProducerService kafkaProducerService;
    private final RabbitMQProducerService rabbitMQProducerService;

    public LoanController(KafkaProducerService kafkaProducerService, RabbitMQProducerService rabbitMQProducerService) {
        this.kafkaProducerService = kafkaProducerService;
        this.rabbitMQProducerService = rabbitMQProducerService;
    }

    @PostMapping("/apply")
    public String applyForLoan(@RequestBody LoanApplication application) {
        kafkaProducerService.sendLoanApplication(application);
        return "Loan application sent!";
    }

    @PostMapping("/notify")
    public String sendNotification(@RequestBody Notification notification) {
        rabbitMQProducerService.sendNotification(notification);
        return "Notification sent!";
    }
}
