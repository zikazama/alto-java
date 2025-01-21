package com.example.transactionsaga.service;

import com.example.transactionsaga.model.Account;
import com.example.transactionsaga.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Ambil detail akun berdasarkan ID.
     *
     * @param accountId ID akun
     * @return Account object
     * @throws IllegalArgumentException jika akun tidak ditemukan
     */
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account with ID " + accountId + " not found"));
    }

    /**
     * Perbarui saldo akun.
     *
     * @param account Account object
     */
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    /**
     * Periksa apakah akun memiliki saldo yang cukup.
     *
     * @param accountId ID akun
     * @param amount Jumlah saldo yang ingin dicek
     * @throws IllegalArgumentException jika saldo tidak cukup
     */
    public void validateSufficientBalance(Long accountId, double amount) {
        Account account = getAccountById(accountId);
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for account ID " + accountId);
        }
    }
}
