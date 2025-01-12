package com.example.demo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class TransactionService {

    private final RedisTemplate<String, Object> redisTemplate;

    public TransactionService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public List<Transaction> getTransactions(String userId) {
        String cacheKey = "transactions:" + userId;

        // Cek apakah data ada di cache
        List<Transaction> transactions = (List<Transaction>) redisTemplate.opsForValue().get(cacheKey);
        if (transactions != null) {
            return transactions;
        }

        // Jika tidak ada di cache, ambil dari database (dummy example)
        transactions = fetchTransactionsFromDatabase(userId);

        // Simpan ke Redis dengan TTL 1 jam
        redisTemplate.opsForValue().set(cacheKey, transactions, Duration.ofHours(1));

        return transactions;
    }

    private List<Transaction> fetchTransactionsFromDatabase(String userId) {
        // Simulasi pengambilan data dari database
        return List.of(new Transaction("TX123", 100.0, LocalDateTime.now()));
    }
}