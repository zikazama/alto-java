package com.example.demo.controller;

import com.example.demo.saga.SagaOrchestrator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions-saga")
public class TransactionSagaController {

    private final SagaOrchestrator sagaOrchestrator;

    public TransactionSagaController(SagaOrchestrator sagaOrchestrator) {
        this.sagaOrchestrator = sagaOrchestrator;
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String accountIdA, @RequestParam String accountIdB, @RequestParam double amount) {
        sagaOrchestrator.executeSaga(accountIdA, accountIdB, amount);
        return "Transaction completed.";
    }
}
