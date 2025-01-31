package com.example.reportservice.repository;

import com.example.reportservice.model.TransactionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransactionReportRepository extends JpaRepository<TransactionReport, Long> {
    Optional<TransactionReport> findByUserId(Long userId);
}
