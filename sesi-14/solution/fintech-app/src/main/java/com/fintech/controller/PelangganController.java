package com.fintech.controller;

import com.fintech.model.Pelanggan;
import com.fintech.repository.PelangganRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {
    private final PelangganRepository pelangganRepository;

    public PelangganController(PelangganRepository pelangganRepository) {
        this.pelangganRepository = pelangganRepository;
    }

    @PostMapping
    public Pelanggan createPelanggan(@RequestBody Pelanggan pelanggan) {
        return pelangganRepository.save(pelanggan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelanggan> getPelangganById(@PathVariable UUID id) {
        return pelangganRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelanggan> updatePelanggan(@PathVariable UUID id, @RequestBody Pelanggan updatedData) {
        return pelangganRepository.findById(id)
                .map(existingPelanggan -> {
                    existingPelanggan.setNama(updatedData.getNama());
                    existingPelanggan.setEmail(updatedData.getEmail());
                    existingPelanggan.setSaldo(updatedData.getSaldo());
                    pelangganRepository.save(existingPelanggan);
                    return ResponseEntity.ok(existingPelanggan);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelanggan(@PathVariable UUID id) {
        if (pelangganRepository.existsById(id)) {
            pelangganRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Pelanggan> getAllPelanggan() {
        return pelangganRepository.findAll();
    }
}
