package com.example.demo.service;

import com.example.demo.rabbitmq.TransactionProducer;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionProducer transactionProducer;

    public TransactionService(TransactionProducer transactionProducer) {
        this.transactionProducer = transactionProducer;
    }

    public void processAndSendTransaction(String transactionData) {
        // Logika tambahan sebelum pengiriman (misalnya validasi)
        transactionProducer.sendTransaction(transactionData);
    }

    public void saveTransaction(String transaction) {
        // Simpan transaksi ke database (implementasi repositori)
        System.out.println("Saving transaction: " + transaction);
    }
}
