package com.example.transactionservice.controller;

import com.example.transactionservice.model.Transaksi;
import com.example.transactionservice.service.TransaksiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService transaksiService;

    @PostMapping("/deposit")
    public ResponseEntity<Transaksi> deposit(@RequestParam Long idPelanggan, @RequestParam Double nominal) {
        Transaksi transaksi = transaksiService.deposit(idPelanggan, nominal);
        return ResponseEntity.ok(transaksi);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaksi> withdraw(@RequestParam Long idPelanggan, @RequestParam Double nominal, @RequestParam Double saldoSaatIni) {
        Transaksi transaksi = transaksiService.withdraw(idPelanggan, nominal, saldoSaatIni);
        return ResponseEntity.ok(transaksi);
    }
}
