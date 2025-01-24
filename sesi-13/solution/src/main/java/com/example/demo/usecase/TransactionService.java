import com.example.demo.adapter.repository.TransactionRepository;
import com.example.demo.adapter.repository.UserRepository;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Transaction deposit(Long userId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        user.setBalance(user.getBalance().add(amount));
        Transaction transaction = new Transaction(null, user, "DEPOSIT", amount, LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}
