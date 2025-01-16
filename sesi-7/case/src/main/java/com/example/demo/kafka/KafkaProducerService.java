package com.example.demo.kafka;

import com.example.demo.dto.LoanApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, LoanApplication> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, LoanApplication> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLoanApplication(LoanApplication application) {
        kafkaTemplate.send("loan-applications", application.getId(), application);
        System.out.println("Sent loan application: " + application);
    }
}
