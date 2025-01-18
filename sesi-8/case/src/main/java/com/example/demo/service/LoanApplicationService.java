package com.example.demo.service;

import com.example.demo.kafka.LoanApplicationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationService {

    @Autowired
    private LoanApplicationProducer loanApplicationProducer;

    public void submitLoanApplication(String loanApplication) {
        // Simpan pengajuan pinjaman ke Redis cache
        loanApplicationProducer.sendLoanApplication(loanApplication);
    }
}
