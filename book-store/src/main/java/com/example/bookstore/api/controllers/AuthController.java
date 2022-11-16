package com.example.bookstore.api.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.dto.LoginRequest;
import com.example.bookstore.dto.RegisterRequest;
import com.example.bookstore.dto.TokenResponseDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.service.auth.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginRequest request) throws Exception {
		return ResponseEntity.ok(this.authService.login(request));
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterRequest request) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.register(request));
	}
	
}
