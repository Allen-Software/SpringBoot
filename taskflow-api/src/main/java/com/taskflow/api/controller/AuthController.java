package com.taskflow.api.controller;

import com.taskflow.api.dto.request.LoginRequest;
import com.taskflow.api.dto.request.RegisterRequest;
import com.taskflow.api.dto.response.AuthResponse;
import com.taskflow.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "使用者註冊與登入相關 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "使用者註冊", description = "建立新的使用者帳號，註冊成功後返回 JWT Token")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

        @PostMapping("/login")
        @Operation(summary = "使用者登入", description = "驗證帳號密碼並取得 JWT Token")
        public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        }
}