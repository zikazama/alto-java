package com.example.demo.saga;

import com.example.demo.service.CreditService;
import com.example.demo.service.DebitService;
import org.springframework.stereotype.Service;

@Service
public class SagaOrchestrator {

    private final DebitService debitService;
    private final CreditService creditService;

    public SagaOrchestrator(DebitService debitService, CreditService creditService) {
        this.debitService = debitService;
        this.creditService = creditService;
    }

    public void executeSaga(String accountIdA, String accountIdB, double amount) {
        try {
            // Step 1: Debit account A
            debitService.debit(accountIdA, amount);

            // Step 2: Credit account B
            creditService.credit(accountIdB, amount);

            System.out.println("Transaction completed successfully.");
        } catch (Exception e) {
            System.out.println("Transaction failed. Initiating rollback...");
            debitService.rollbackDebit(accountIdA, amount);
        }
    }
}
