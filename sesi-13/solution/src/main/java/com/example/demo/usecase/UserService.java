import com.example.demo.adapter.repository.TransactionRepository;
import com.example.demo.adapter.repository.UserRepository;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(String email, String fullName, BigDecimal initialBalance) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }
        User user = new User(null, email, fullName, initialBalance == null ? BigDecimal.ZERO : initialBalance);
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found."));
    }
}