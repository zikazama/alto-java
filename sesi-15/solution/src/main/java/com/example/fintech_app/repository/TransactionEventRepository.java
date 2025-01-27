package com.example.fintech_app.repository;

import com.example.fintech_app.model.TransactionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionEventRepository extends JpaRepository<TransactionEvent, Long> {
}
