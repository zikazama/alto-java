package com.fintech.factory;

import com.fintech.model.Transaksi;
import com.fintech.model.Pelanggan;

import java.time.LocalDateTime;

public class TransaksiFactory {

    public static Transaksi createTransaksi(Pelanggan pelanggan, String jenisTransaksi, double nominal) {
        return new Transaksi(pelanggan, jenisTransaksi, nominal, LocalDateTime.now());
    }
}
