package com.insurance.auth_service.controller;

import com.insurance.auth_service.dto.LoginRequestDto;
import com.insurance.auth_service.dto.LoginResponseDto;
import com.insurance.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto request) {

        return ResponseEntity.ok(authService.login(request));
    }
}
