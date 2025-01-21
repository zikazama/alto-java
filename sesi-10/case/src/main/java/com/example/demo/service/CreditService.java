package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class CreditService {

    public void credit(String accountId, double amount) {
        System.out.println("Crediting " + amount + " to account: " + accountId);
        // Logic to credit the account
        if (amount > 1000) {
            throw new RuntimeException("Simulated failure in Credit Service");
        }
    }
}
