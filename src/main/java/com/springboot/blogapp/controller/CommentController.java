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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {
	private CommentService commentService;
	public CommentController(CommentService commentService) {
		this.commentService=commentService;
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable (value="postId") Long postId,@Valid @RequestBody CommentDto commentDto){
		return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getAllCommentsByPostId(@PathVariable (value="postId") Long postId){
		return  commentService.getAllCommentsByPostId(postId);
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable (value="postId") Long postId,
			@PathVariable(value="commentId") Long commentId
			) { 
		return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
	}
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(
			@PathVariable (value="postId") Long postId,
			@PathVariable (value="commentId") Long commentId,
			@Valid @RequestBody CommentDto commentDto
			){
		return new ResponseEntity<CommentDto>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
	}
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable (value="postId") Long postId,
			@PathVariable(value="commentId") Long commentId) {
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<String>("Comment deleted successfully",HttpStatus.OK) ;
	}
}
