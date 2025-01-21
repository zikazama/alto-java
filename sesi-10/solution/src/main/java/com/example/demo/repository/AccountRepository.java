package com.example.transactionsaga.repository;

import com.example.transactionsaga.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
