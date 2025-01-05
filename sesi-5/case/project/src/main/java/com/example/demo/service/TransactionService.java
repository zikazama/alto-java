package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void performTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}