package com.example.transactionsaga.service;

import com.example.transactionsaga.model.Account;
import com.example.transactionsaga.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditService {
    private final AccountRepository accountRepository;

    public CreditService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void credit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }
}
