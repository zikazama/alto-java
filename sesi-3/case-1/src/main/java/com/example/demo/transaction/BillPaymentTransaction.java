package com.example.demo.transaction;

import com.example.demo.model.Transaction;
import org.springframework.stereotype.Component;

@Component("billPayment")
public class BillPaymentTransaction implements TransactionStrategy {
    @Override
    public void processTransaction(Transaction transaction) {
        // Logic for processing bill payment
        System.out.println("Processing bill payment transaction: " + transaction);
    }
}