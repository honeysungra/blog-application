package com.springboot.blogapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapp.payload.CommentDto;
import com.springboot.blogapp.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "CRUD API for Comment Resource")
public class CommentController {
	private CommentService commentService;
	public CommentController(CommentService commentService) {
		this.commentService=commentService;
	}
	
	@Operation(
			summary = "Create Comment REST API",
			description = "Create Comment REST API is used to save comment into database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 Created"
			)
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable (value="postId") Long postId,@Valid @RequestBody CommentDto commentDto){
		return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}
	
	@Operation(
			summary = "Get All Comment from Post ID REST API",
			description = "Get All Comment from Post ID  REST API is used to get all comment from database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getAllCommentsByPostId(@PathVariable (value="postId") Long postId){
		return  commentService.getAllCommentsByPostId(postId);
	}
	@Operation(
			summary = "Get Comment By ID REST API",
			description = "Get Comment By Id REST API is used to get single comment from database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable (value="postId") Long postId,
			@PathVariable(value="commentId") Long commentId
			) { 
		return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
	}
	
	@Operation(
			summary = "Update Comment in POST By ID REST API",
			description = "Update Comment in Post By Id REST API is used to update comment in the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(
			@PathVariable (value="postId") Long postId,
			@PathVariable (value="commentId") Long commentId,
			@Valid @RequestBody CommentDto commentDto
			){
		return new ResponseEntity<CommentDto>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
	}
	
	@Operation(
			summary = "Delete Comment By ID REST API",
			description = "Delete Comment  By Id REST API is used to delete comment from the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable (value="postId") Long postId,
			@PathVariable(value="commentId") Long commentId) {
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<String>("Comment deleted successfully",HttpStatus.OK) ;
	}
}
