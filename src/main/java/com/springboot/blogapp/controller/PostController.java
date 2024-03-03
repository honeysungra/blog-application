package com.springboot.blogapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapp.payload.PostDto;
import com.springboot.blogapp.payload.PostResponse;
import com.springboot.blogapp.service.PostService;
import com.springboot.blogapp.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "CRUD API for POST Resource")
public class PostController {
	private PostService postService;

	public PostController(PostService postService) {
		
		this.postService = postService;
	}
	@Operation(
			summary = "Create POST REST API",
			description = "Create Post REST API is used to save post into database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 Created"
			)
	
	@SecurityRequirement(
			name = "Bear Authentication"
			)
	//create Blog Post Rest API
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	@Operation(
			summary = "Get All Post REST API",
			description = "Get  All Post REST API is used to get all posts from database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	//Get All Blog Post Rest API
	@GetMapping
	public PostResponse getallPosts(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value="sortDir", defaultValue =AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			)
	{
		return postService.getAllPosts(pageNo, pageSize,sortBy,sortDir);
	}
	
	@Operation(
			summary = "Get POST By ID REST API",
			description = "Get  Post By Id REST API is used to get single post from database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	//Get Post By Id Rest API
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable long id){
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	@Operation(
			summary = "Update POST By ID REST API",
			description = "Update Post By Id REST API is used to update post in the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	
	@SecurityRequirement(
			name = "Bear Authentication"
		)
	//update post by id
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable long id){
//		PostDto postResponse= postService.updatePost(postDto, id);
//		return new ResponseEntity<>(postResponse,HttpStatus.OK);
		return ResponseEntity.ok(postService.updatePost(postDto, id));
	}
	
	

	@Operation(
			summary = "Delete POST By ID REST API",
			description = "Delete Post By Id REST API is used to delete post from the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	@SecurityRequirement(
			name = "Bear Authentication"
		)
	//delete post by id
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable long id){
		postService.deletePostById(id);
		return new ResponseEntity<String>("Post Deleted Successfully", HttpStatus.OK);
	}
	//Buld get post by category
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Long categoryId){
		List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
		return ResponseEntity.ok(postDtos);
	}
}
