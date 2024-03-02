package com.springboot.blogapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema (
		description = "Login Information"
		)

public class LoginDto {
	@Schema(
			description = "Username or Email"
			)
	private String usernameOrEmail;
	@Schema(
			description = "Password"
			)
	private String password;
}
