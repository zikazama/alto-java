package com.example.transactionsaga.service;

import com.example.transactionsaga.model.Account;
import com.example.transactionsaga.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DebitService {
    private final AccountRepository accountRepository;

    public DebitService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void debit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }
}
