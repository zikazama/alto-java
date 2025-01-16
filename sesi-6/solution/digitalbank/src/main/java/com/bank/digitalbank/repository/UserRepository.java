// src/main/java/com/bank/digitalbank/repository/UserRepository.java
package com.bank.digitalbank.repository;

import com.bank.digitalbank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
