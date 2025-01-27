package com.example.reportservice.controller;

import com.example.reportservice.dto.LaporanTransaksiDTO;
import com.example.reportservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/laporan")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/transaksi")
    public ResponseEntity<List<LaporanTransaksiDTO>> getReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<LaporanTransaksiDTO> report = reportService.generateReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
