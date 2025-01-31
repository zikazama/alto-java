package com.example.reportservice.service;

import com.example.reportservice.model.TransactionReport;
import com.example.reportservice.repository.TransactionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReportService {

    private final TransactionReportRepository reportRepository;

    @Autowired
    public ReportService(TransactionReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @KafkaListener(topics = "transactions-analytics", groupId = "report-consumer-group")
    @Transactional
    public void processTransaction(String message) {
        String[] data = message.split(",");
        Long userId = Long.parseLong(data[0]);
        BigDecimal amount = new BigDecimal(data[1]);

        TransactionReport report = reportRepository.findByUserId(userId)
                .orElse(new TransactionReport(userId, 0, BigDecimal.ZERO));

        report.setTotalTransactions(report.getTotalTransactions() + 1);
        report.setTotalAmount(report.getTotalAmount().add(amount));
        report.setLastUpdated(LocalDateTime.now());

        reportRepository.save(report);
    }
}
