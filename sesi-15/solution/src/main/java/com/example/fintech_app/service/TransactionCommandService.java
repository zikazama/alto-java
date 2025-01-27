package com.example.fintech_app.service;

import com.example.fintech_app.model.Transaction;
import com.example.fintech_app.model.TransactionEvent;
import com.example.fintech_app.repository.TransactionEventRepository;
import com.example.fintech_app.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionCommandService {
    private final TransactionRepository transactionRepository;
    private final TransactionEventRepository transactionEventRepository;

    public TransactionCommandService(TransactionRepository transactionRepository, TransactionEventRepository transactionEventRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionEventRepository = transactionEventRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Log event for Event Sourcing
        TransactionEvent event = new TransactionEvent();
        event.setTransactionId(savedTransaction.getId());
        TransactionEvent.EventType eventType = TransactionEvent.EventType.valueOf("CREATED".toUpperCase());
        event.setEventType(eventType);

        transactionEventRepository.save(event);

        return savedTransaction;
    }
}
