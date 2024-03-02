package com.springboot.blogapp.payload;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
		description = "PostDto Model Information"
		)
public class PostDto {
	private Long id;
	
	//title should not be null or empty 
	//title should have atleast 2 characters
	@Schema(
			description = "Blog Post Title"
			)
	
	@NotEmpty
	@Size(min = 2, message = "Post Title should have atleast 2 characters")
	private String title;
	
	//post description should not be null or empty
	//postd description shoyld have atleast 10 charcters
	@Schema(
			description = "Blog Post Description"
			)
	@NotEmpty
	@Size(min=10, message = "Post Description should have atleast 10 characters")
	private String description;
	
	@Schema(
			description = "Blog Post Content"
			)
	@NotEmpty
	private String content;
	
	@Schema(
			description = "Blog Post Comments"
			)

	private Set<CommentDto> comments;
	
	@Schema(
			description = "Blog Post Category"
			)

	private Long categoryId;
}
