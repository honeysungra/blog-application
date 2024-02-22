package com.springboot.blogapp.service;

import java.util.List;

import com.springboot.blogapp.payload.CommentDto;


public interface CommentService {
	CommentDto createComment(Long postId,CommentDto commentDto);
	
	List<CommentDto> getAllCommentsByPostId(Long postId);
	
	CommentDto getCommentById(Long postId, Long commentId);
	
	CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
	
	void deleteComment(Long postId, Long commentId);
}
