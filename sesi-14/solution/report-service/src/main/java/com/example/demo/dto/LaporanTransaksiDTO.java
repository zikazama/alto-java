package com.example.reportservice.dto;

public class LaporanTransaksiDTO {

    private Long idPelanggan;
    private Double totalNominal;
    private Double saldoAkhir;

    public LaporanTransaksiDTO(Long idPelanggan, Double totalNominal, Double saldoAkhir) {
        this.idPelanggan = idPelanggan;
        this.totalNominal = totalNominal;
        this.saldoAkhir = saldoAkhir;
    }

    // Getters and Setters
    public Long getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(Long idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public Double getTotalNominal() {
        return totalNominal;
    }

    public void setTotalNominal(Double totalNominal) {
        this.totalNominal = totalNominal;
    }

    public Double getSaldoAkhir() {
        return saldoAkhir;
    }

    public void setSaldoAkhir(Double saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
    }
}
