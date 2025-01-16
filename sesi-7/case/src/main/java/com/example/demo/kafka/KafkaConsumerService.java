package com.example.demo.kafka;

import com.example.demo.dto.LoanApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "loan-applications", groupId = "loan-group")
    public void processLoanApplication(LoanApplication application) {
        System.out.println("Processing loan application: " + application);
        // Add business logic here
    }
}
