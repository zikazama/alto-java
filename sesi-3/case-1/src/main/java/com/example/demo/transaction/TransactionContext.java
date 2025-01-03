package com.example.demo.transaction;

import com.example.demo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransactionContext {
    private final Map<String, TransactionStrategy> strategies;

    @Autowired
    public TransactionContext(Map<String, TransactionStrategy> strategies) {
        this.strategies = strategies;
    }

    public void executeTransaction(String type, Transaction transaction) {
        TransactionStrategy strategy = strategies.get(type);
        if (strategy != null) {
            strategy.processTransaction(transaction);
        } else {
            throw new IllegalArgumentException("Unknown transaction type: " + type);
        }
    }
}