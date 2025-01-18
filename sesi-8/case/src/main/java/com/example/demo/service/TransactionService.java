package com.example.demo.service;

import com.example.demo.kafka.TransactionProducer;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionProducer transactionProducer;

    public TransactionService(TransactionProducer transactionProducer) {
        this.transactionProducer = transactionProducer;
    }

    public void processAndSendTransaction(String transactionData) {
        // Logika tambahan sebelum pengiriman (misalnya validasi)
        transactionProducer.sendTransaction(transactionData);
    }
}
