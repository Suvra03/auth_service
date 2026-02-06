package com.insurance.auth_service.service;

import com.insurance.auth_service.dto.LoginRequestDto;
import com.insurance.auth_service.dto.LoginResponseDto;
import com.insurance.auth_service.entity.UserAuth;
import com.insurance.auth_service.exception.AuthenticationException;
import com.insurance.auth_service.repository.UserAuthRepository;
import com.insurance.auth_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponseDto login(LoginRequestDto request) {

        // Find user by email
        UserAuth user = userAuthRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationException("Invalid email or password"));

        // Validate password (BCrypt)
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new AuthenticationException("Invalid email or password");
        }

        // Check account status
        if (!"ACTIVE".equalsIgnoreCase(user.getAccountStatus())) {
            throw new AuthenticationException("Account is blocked");
        }

        // Update last login
        user.setLastLogin(LocalDateTime.now());
        userAuthRepository.save(user);

        // Generate JWT token
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getCustomerId(),
                user.getRole());

        // Return response
        return LoginResponseDto.builder()
                .token(token)
                .customerId(user.getCustomerId())
                .email(user.getEmail())
                .build();
    }
}