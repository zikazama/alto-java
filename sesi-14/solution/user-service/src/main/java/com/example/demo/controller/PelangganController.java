package com.example.userservice.controller;

import com.example.userservice.model.Pelanggan;
import com.example.userservice.service.PelangganService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {

    @Autowired
    private PelangganService pelangganService;

    @PostMapping
    public ResponseEntity<Pelanggan> createPelanggan(@Valid @RequestBody Pelanggan pelanggan) {
        return ResponseEntity.ok(pelangganService.createPelanggan(pelanggan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pelanggan>> getPelangganById(@PathVariable Long id) {
        return ResponseEntity.ok(pelangganService.getPelangganById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelanggan> updatePelanggan(@PathVariable Long id, @Valid @RequestBody Pelanggan pelangganBaru) {
        return ResponseEntity.ok(pelangganService.updatePelanggan(id, pelangganBaru));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePelanggan(@PathVariable Long id) {
        pelangganService.deletePelanggan(id);
        return ResponseEntity.ok("Pelanggan berhasil dihapus");
    }
}
