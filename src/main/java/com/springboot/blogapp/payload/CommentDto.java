package com.springboot.blogapp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private Long id;
	@NotEmpty(message="Name should be null or empty")
	
	private String name;
	@NotEmpty(message="Email should be null or empty")
	@Email
	private String email;
	@NotEmpty(message="Body should be null or empty")
	@Size(min=10, message="Comment body must be minimum of 5 characters")
	private String body;
}
