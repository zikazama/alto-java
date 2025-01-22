package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class DebitService {

    public void debit(String accountId, double amount) {
        System.out.println("Debiting " + amount + " from account: " + accountId);
        // Logic to debit the account
    }

    public void rollbackDebit(String accountId, double amount) {
        System.out.println("Rolling back debit of " + amount + " for account: " + accountId);
        // Logic to reverse debit
    }
}
