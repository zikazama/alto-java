package com.example.demo.usecase;

import com.example.demo.adapter.repository.TransactionRepository;
import com.example.demo.adapter.repository.UserRepository;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Transaction deposit(Long userId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        user.setBalance(user.getBalance().add(amount));
        return transactionRepository.save(new Transaction(null, user, "DEPOSIT", amount, LocalDateTime.now()));
    }

    @Transactional
    public Transaction withdraw(Long userId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        if (user.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        user.setBalance(user.getBalance().subtract(amount));
        return transactionRepository.save(new Transaction(null, user, "WITHDRAW", amount, LocalDateTime.now()));
    }

    @Transactional
    public Transaction transfer(Long fromUserId, Long toUserId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new IllegalArgumentException("Source user not found."));
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new IllegalArgumentException("Destination user not found."));
        if (fromUser.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance for transfer.");
        }
        fromUser.setBalance(fromUser.getBalance().subtract(amount));
        toUser.setBalance(toUser.getBalance().add(amount));
        transactionRepository.save(new Transaction(null, fromUser, "TRANSFER", amount, LocalDateTime.now()));
        return transactionRepository.save(new Transaction(null, toUser, "TRANSFER_RECEIVED", amount, LocalDateTime.now()));
    }

    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
}
