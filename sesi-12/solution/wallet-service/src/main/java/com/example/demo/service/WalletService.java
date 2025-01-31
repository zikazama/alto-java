package com.example.walletservice.service;

import com.example.walletservice.model.Wallet;
import com.example.walletservice.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class WalletService {

    private static final String WALLET_CACHE_PREFIX = "wallet_balance_";

    private final WalletRepository walletRepository;
    private final RedisTemplate<String, BigDecimal> redisTemplate;

    @Autowired
    public WalletService(WalletRepository walletRepository, RedisTemplate<String, BigDecimal> redisTemplate) {
        this.walletRepository = walletRepository;
        this.redisTemplate = redisTemplate;
    }

    public BigDecimal getBalance(Long userId) {
        String cacheKey = WALLET_CACHE_PREFIX + userId;
        BigDecimal cachedBalance = redisTemplate.opsForValue().get(cacheKey);

        if (cachedBalance != null) {
            return cachedBalance;
        }

        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        redisTemplate.opsForValue().set(cacheKey, wallet.getBalance(), 10, TimeUnit.MINUTES);
        return wallet.getBalance();
    }

    @Transactional
    public void topUp(Long userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElse(new Wallet(userId, BigDecimal.ZERO));

        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
        
        redisTemplate.opsForValue().set(WALLET_CACHE_PREFIX + userId, wallet.getBalance(), 10, TimeUnit.MINUTES);
    }

    @Transactional
    public void deductBalance(Long userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);

        redisTemplate.opsForValue().set(WALLET_CACHE_PREFIX + userId, wallet.getBalance(), 10, TimeUnit.MINUTES);
    }
}
