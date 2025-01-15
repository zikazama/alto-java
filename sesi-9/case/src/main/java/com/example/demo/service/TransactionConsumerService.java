package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumerService {

    private final TransactionRepository transactionRepository;

    public TransactionConsumerService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @KafkaListener(topics = "transactions", groupId = "group_id")
    public void consumeTransaction(String transaction) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Transaction newTransaction = objectMapper.readValue(transaction, Transaction.class);
            transactionRepository.save(newTransaction);
            System.out.println("Transaction saved: " + newTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
