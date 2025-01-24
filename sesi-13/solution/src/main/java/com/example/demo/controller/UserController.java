import com.example.demo.adapter.repository.TransactionRepository;
import com.example.demo.adapter.repository.UserRepository;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String fullName,
                                           @RequestParam String email,
                                           @RequestParam(required = false) BigDecimal initialBalance) {
        User user = userService.createUser(fullName, email, initialBalance);
        return ResponseEntity.ok(user);
    }
}
