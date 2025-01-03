package com.example.demo.transaction;

import com.example.demo.model.Transaction;
import org.springframework.stereotype.Component;

@Component("transfer")
public class TransferTransaction implements TransactionStrategy {
    @Override
    public void processTransaction(Transaction transaction) {
        // Logic for processing transfer
        System.out.println("Processing transfer transaction: " + transaction);
    }
}