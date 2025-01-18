package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationConsumer {

    @KafkaListener(topics = "loan-applications", groupId = "loan-application-consumer")
    public void consume(String loanApplication) {
        // Proses pengajuan pinjaman
        System.out.println("Menerima pengajuan pinjaman: " + loanApplication);
    }
}
