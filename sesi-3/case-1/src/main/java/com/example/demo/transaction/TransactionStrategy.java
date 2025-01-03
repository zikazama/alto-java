package com.example.demo.transaction;

import com.example.demo.model.Transaction;

public interface TransactionStrategy {
    void processTransaction(Transaction transaction);
}