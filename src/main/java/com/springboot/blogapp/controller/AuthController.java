package com.springboot.blogapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapp.payload.LoginDto;
import com.springboot.blogapp.payload.RegisterDto;
import com.springboot.blogapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping(value = {"/login","/signin"})
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
		String response=authService.login(loginDto);
		return ResponseEntity.ok(response);
	}
	@PostMapping(value={"/signup","register"})
	public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto){
		String response = authService.register(registerDto);
		return ResponseEntity.ok(response);
	}
}
