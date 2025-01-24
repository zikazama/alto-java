package com.example.demo.adapter.repository;

import com.example.demo.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Tambahkan method custom jika diperlukan
}
