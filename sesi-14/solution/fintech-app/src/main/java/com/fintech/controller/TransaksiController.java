package com.fintech.controller;

import com.fintech.model.Transaksi;
import com.fintech.service.TransaksiService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    private final TransaksiService transaksiService;

    public TransaksiController(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }

    @PostMapping("/{idPelanggan}/deposito")
    public ResponseEntity<Transaksi> buatDeposit(
            @PathVariable UUID idPelanggan,
            @RequestParam double nominal) {
        Transaksi transaksi = transaksiService.buatTransaksi(idPelanggan, "Deposit", nominal);
        return ResponseEntity.ok(transaksi);
    }

    @PostMapping("/{idPelanggan}/penarikan")
    public ResponseEntity<Transaksi> buatPenarikan(
            @PathVariable UUID idPelanggan,
            @RequestParam double nominal) {
        Transaksi transaksi = transaksiService.buatTransaksi(idPelanggan, "Penarikan", nominal);
        return ResponseEntity.ok(transaksi);
    }

    @GetMapping("/{idPelanggan}/laporan")
    public ResponseEntity<List<Transaksi>> getLaporanTransaksi(
            @PathVariable UUID idPelanggan,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Transaksi> laporan = transaksiService.getTransaksiByPelanggan(idPelanggan, startDate, endDate);
        return ResponseEntity.ok(laporan);
    }
}
