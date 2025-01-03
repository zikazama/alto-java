package com.example.demo.transaction;

import com.example.demo.model.Transaction;
import org.springframework.stereotype.Component;

@Component("purchase")
public class PurchaseTransaction implements TransactionStrategy {
    @Override
    public void processTransaction(Transaction transaction) {
        // Logic for processing purchase
        System.out.println("Processing purchase transaction: " + transaction);
    }
}