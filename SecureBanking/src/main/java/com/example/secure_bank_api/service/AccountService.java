package com.example.secure_bank_api.service;

import com.example.secure_bank_api.model.Account;
import com.example.secure_bank_api.repository.AccountRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) { this.accountRepository = accountRepository; }
    public BigDecimal balance(String username) {
        Account a = accountRepository.findByUserUsername(username).orElseThrow(() -> new IllegalArgumentException("notfound"));
        return a.getBalance();
    }
    @Transactional
    @Retry(name = "withdraw")
    @CircuitBreaker(name = "withdraw")
    public BigDecimal withdraw(String username, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("invalid");
        Account a = accountRepository.findByUserUsername(username).orElseThrow(() -> new IllegalArgumentException("notfound"));
        if (a.getBalance().compareTo(amount) < 0) throw new IllegalStateException("insufficient");
        a.setBalance(a.getBalance().subtract(amount));
        accountRepository.save(a);
        return a.getBalance();
    }
}
