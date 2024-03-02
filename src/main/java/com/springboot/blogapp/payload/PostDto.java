package com.springboot.blogapp.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
	private Long id;
	
	//title should not be null or empty 
	//title should have atleast 2 characters
	@NotEmpty
	@Size(min = 2, message = "Post Title should have atleast 2 characters")
	private String title;
	
	//post description should not be null or empty
	//postd description shoyld have atleast 10 charcters
	@NotEmpty
	@Size(min=10, message = "Post Description should have atleast 10 characters")
	private String description;
	
	@NotEmpty
	private String content;
	
	private Set<CommentDto> comments;
	
	private Long categoryId;
}
