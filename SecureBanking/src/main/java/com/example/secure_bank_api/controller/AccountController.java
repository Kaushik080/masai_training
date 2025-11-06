package com.example.secure_bank_api.controller;

import com.example.secure_bank_api.dto.WithdrawRequest;
import com.example.secure_bank_api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) { this.accountService = accountService; }
    @GetMapping("/balance")
    public ResponseEntity<?> balance(Authentication auth) {
        BigDecimal b = accountService.balance(auth.getName());
        return ResponseEntity.ok(b);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(Authentication auth, @Valid @RequestBody WithdrawRequest req) {
        BigDecimal b = accountService.withdraw(auth.getName(), req.getAmount());
        return ResponseEntity.ok(b);
    }
}
