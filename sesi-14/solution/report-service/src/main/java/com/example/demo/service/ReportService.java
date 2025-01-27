package com.example.reportservice.service;

import com.example.reportservice.dto.LaporanTransaksiDTO;
import com.example.reportservice.model.Transaksi;
import com.example.reportservice.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    public List<LaporanTransaksiDTO> generateReport(LocalDateTime startDate, LocalDateTime endDate) {
        List<Transaksi> transaksiList = transaksiRepository.findAllByTanggalTransaksiBetween(startDate, endDate);

        return transaksiList.stream()
                .collect(Collectors.groupingBy(Transaksi::getIdPelanggan))
                .entrySet()
                .stream()
                .map(entry -> {
                    Long idPelanggan = entry.getKey();
                    List<Transaksi> transaksiPelanggan = entry.getValue();
                    Double totalNominal = transaksiPelanggan.stream().mapToDouble(Transaksi::getNominal).sum();
                    Double saldoAkhir = transaksiPelanggan.stream()
                            .mapToDouble(t -> t.getJenisTransaksi().equals("DEPOSIT") ? t.getNominal() : -t.getNominal())
                            .sum();
                    return new LaporanTransaksiDTO(idPelanggan, totalNominal, saldoAkhir);
                })
                .collect(Collectors.toList());
    }
}
