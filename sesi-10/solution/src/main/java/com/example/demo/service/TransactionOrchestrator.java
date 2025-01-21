package com.example.transactionsaga.service;

import org.springframework.stereotype.Service;

@Service
public class TransactionOrchestrator {
    private final DebitService debitService;
    private final CreditService creditService;

    public TransactionOrchestrator(DebitService debitService, CreditService creditService) {
        this.debitService = debitService;
        this.creditService = creditService;
    }

    public String processTransaction(Long accountIdFrom, Long accountIdTo, double amount) {
        try {
            debitService.debit(accountIdFrom, amount);
            // Simulate potential failure in credit step
            if (Math.random() < 0.5) {
                throw new RuntimeException("Simulated failure in Credit Service");
            }
            creditService.credit(accountIdTo, amount);
            return "success";
        } catch (Exception e) {
            debitService.debit(accountIdTo, -amount); // Rollback debit operation
            return "failed";
        }
    }
}
