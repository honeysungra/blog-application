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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "API for Login and Register Resource")
public class AuthController {
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	@Operation(
			summary = "Login REST API",
			description = "Login REST API is used to login and generate JWT Token"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	
	@PostMapping(value = {"/login","/signin"})
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
		String token=authService.login(loginDto);
		
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}
	@Operation(
			summary = "Register REST API",
			description = "Register REST API is used to register new user"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)

	@PostMapping(value={"/signup","register"})
	public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto){
		String response = authService.register(registerDto);
		return ResponseEntity.ok(response);
	}
}
