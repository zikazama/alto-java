package com.transaction.service;

import com.transaction.dto.TransactionRequest;
import com.transaction.entity.Transaction;
import com.transaction.repository.TransactionRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final RabbitTemplate rabbitTemplate;

    public TransactionService(TransactionRepository transactionRepository, RabbitTemplate rabbitTemplate) {
        this.transactionRepository = transactionRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Transaction createTransaction(TransactionRequest request) {
        // Validate transaction details
        Transaction transaction = new Transaction();
        transaction.setSender(request.getSender());
        transaction.setReceiver(request.getReceiver());
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setStatus("SUCCESS");
        transaction.setCreatedAt(new Date());

        // Save to database
        transaction = transactionRepository.save(transaction);

        // Send notification to RabbitMQ
        rabbitTemplate.convertAndSend("transactionQueue", transaction);

        return transaction;
    }
}
