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
@Schema(
		description = "RegisterDto Model Information"
		)
public class RegisterDto {
	
	private String name;
	private String email;
	private String username;
	private String password;
	
}
