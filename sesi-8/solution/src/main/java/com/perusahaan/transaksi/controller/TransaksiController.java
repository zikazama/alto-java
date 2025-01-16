// src/main/java/com/perusahaan/transaksi/controller/TransaksiController.java
package com.perusahaan.transaksi.controller;

import com.perusahaan.transaksi.dto.TransaksiRequest;
import com.perusahaan.transaksi.dto.TransaksiResponse;
import com.perusahaan.transaksi.service.TransaksiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaksi")
@RequiredArgsConstructor
public class TransaksiController {

    private final TransaksiService transaksiService;

    @PostMapping
    public ResponseEntity<TransaksiResponse> kirimTransaksi(@Valid @RequestBody TransaksiRequest transaksiRequest) {
        try {
            transaksiService.kirimTransaksi(transaksiRequest);
            TransaksiResponse response = new TransaksiResponse("SUCCESS", "Transaksi telah dikirim untuk diproses.");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            TransaksiResponse response = new TransaksiResponse("ERROR", "Gagal mengirim transaksi.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
