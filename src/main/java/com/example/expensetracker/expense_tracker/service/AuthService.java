package com.example.expensetracker.expense_tracker.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expensetracker.dto.AuthRequest;
import com.example.expensetracker.dto.AuthResponse;
import com.example.expensetracker.dto.RegisterRequest;
import com.example.expensetracker.expense_tracker.entity.User;
import com.example.expensetracker.expense_tracker.repository.UserRepository;
import com.example.expensetracker.expense_tracker.security.JwtUtil;

// import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        User u = User.builder()
                .email(req.getEmail().toLowerCase().trim())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .name(req.getName().trim())
                .build();

        userRepository.save(u);

        String token = jwtUtil.generateToken(u.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest req) {

        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // check password
        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        // generate token
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

}
