package com.example.secure_bank_api.service;

import com.example.secure_bank_api.dto.AuthRequest;
import com.example.secure_bank_api.model.Account;
import com.example.secure_bank_api.model.User;
import com.example.secure_bank_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) { this.userRepository = userRepository; this.passwordEncoder = passwordEncoder; }
    @Transactional
    public User register(AuthRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) throw new IllegalArgumentException("exists");
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        Account a = new Account();
        a.setUser(u);
        a.setBalance(BigDecimal.ZERO);
        u.setAccount(a);
        return userRepository.save(u);
    }
    public User authenticate(String username, String password) {
        User u = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("bad"));
        if (!passwordEncoder.matches(password, u.getPassword())) throw new IllegalArgumentException("bad");
        return u;
    }
}
