package com.springboot.blogapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
		description = "JWT Token"
		)

public class JWTAuthResponse {
	@Schema(
			description = "JWT Access Token"
			)
	private String accessToken;
	@Schema(
			description = "JWT Token Type"
			)
	private String tokenType="Bearer";
}
