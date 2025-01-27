package com.example.userservice.service;

import com.example.userservice.model.Pelanggan;
import com.example.userservice.repository.PelangganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PelangganService {

    @Autowired
    private PelangganRepository pelangganRepository;

    public Pelanggan createPelanggan(Pelanggan pelanggan) {
        if (pelangganRepository.existsByEmail(pelanggan.getEmail())) {
            throw new IllegalArgumentException("Email sudah digunakan");
        }
        return pelangganRepository.save(pelanggan);
    }

    public Optional<Pelanggan> getPelangganById(Long id) {
        return pelangganRepository.findById(id);
    }

    public Pelanggan updatePelanggan(Long id, Pelanggan pelangganBaru) {
        Pelanggan pelanggan = pelangganRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pelanggan tidak ditemukan"));
        pelanggan.setNama(pelangganBaru.getNama());
        pelanggan.setEmail(pelangganBaru.getEmail());
        return pelangganRepository.save(pelanggan);
    }

    public void deletePelanggan(Long id) {
        pelangganRepository.deleteById(id);
    }
}
