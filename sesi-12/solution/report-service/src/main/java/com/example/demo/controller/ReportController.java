package com.example.reportservice.controller;

import com.example.reportservice.model.TransactionReport;
import com.example.reportservice.repository.TransactionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final TransactionReportRepository reportRepository;

    @Autowired
    public ReportController(TransactionReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/{userId}")
    public Optional<TransactionReport> getUserReport(@PathVariable Long userId) {
        return reportRepository.findByUserId(userId);
    }
}
