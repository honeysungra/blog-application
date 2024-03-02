package com.springboot.blogapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
		description = "CommentDto Model Information"
		)

public class CommentDto {
	private Long id;
	
	@Schema(
			description = "Commentor Name"
			)

	@NotEmpty(message="Name should be null or empty")
	private String name;
	
	@Schema(
			description = "Commentor Email"
			)
	@NotEmpty(message="Email should be null or empty")
	@Email
	private String email;
	
	@Schema(
			description = "Comment Body"
			)
	@NotEmpty(message="Body should be null or empty")
	@Size(min=10, message="Comment body must be minimum of 5 characters")
	private String body;
}
