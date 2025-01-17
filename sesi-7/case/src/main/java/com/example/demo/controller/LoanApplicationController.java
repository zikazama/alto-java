package com.example.demo.controller;

import com.example.demo.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/apply")
    public String applyForLoan(@RequestBody String loanApplication) {
        loanApplicationService.submitLoanApplication(loanApplication);
        return "Pengajuan pinjaman berhasil diterima";
    }
}
