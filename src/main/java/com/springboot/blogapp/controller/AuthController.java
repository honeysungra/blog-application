package com.springboot.blogapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapp.payload.JWTAuthResponse;
import com.springboot.blogapp.payload.LoginDto;
import com.springboot.blogapp.payload.RegisterDto;
import com.springboot.blogapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping(value = {"/login","/signin"})
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
		String token=authService.login(loginDto);
		
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}
	@PostMapping(value={"/signup","register"})
	public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto){
		String response = authService.register(registerDto);
		return ResponseEntity.ok(response);
	}
}
