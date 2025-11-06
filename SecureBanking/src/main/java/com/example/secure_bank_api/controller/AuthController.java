package com.example.secure_bank_api.controller;

import com.example.secure_bank_api.dto.AuthRequest;
import com.example.secure_bank_api.dto.AuthResponse;
import com.example.secure_bank_api.model.User;
import com.example.secure_bank_api.security.JwtUtil;
import com.example.secure_bank_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public AuthController(UserService userService, JwtUtil jwtUtil) { this.userService = userService; this.jwtUtil = jwtUtil; }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest request) {
        User u = userService.register(request);
        String token = jwtUtil.generateToken(u.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        User u = userService.authenticate(request.getUsername(), request.getPassword());
        String token = jwtUtil.generateToken(u.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
