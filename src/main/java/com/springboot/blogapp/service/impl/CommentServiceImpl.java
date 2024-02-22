package com.springboot.blogapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blogapp.entity.Comment;
import com.springboot.blogapp.entity.Post;
import com.springboot.blogapp.exception.BlogApiException;
import com.springboot.blogapp.exception.ResourceNotFoundException;
import com.springboot.blogapp.payload.CommentDto;
import com.springboot.blogapp.repository.CommentRepository;
import com.springboot.blogapp.repository.PostRepository;
import com.springboot.blogapp.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
	
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper modelMapper;
	public CommentServiceImpl (CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
		this.commentRepository=commentRepository;
		this.postRepository=postRepository;
		this.modelMapper= modelMapper;
	}
	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		// TODO Auto-generated method stub
		Comment comment= mapToEntity(commentDto);
		Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		comment.setPost(post);
		Comment newComment= commentRepository.save(comment);
		
		return mapToDTO(newComment);
	}
	@Override
	public List<CommentDto> getAllCommentsByPostId(Long postId) {
		List<Comment> comments= commentRepository.findByPostId(postId);
		//convert List of Comment Entities to list of Comment DTO
		return comments.stream().map(comment-> mapToDTO(comment)).collect(Collectors.toList());
	}
	
	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		//retrieve post by id
		Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		//retrieve comment by id
		Comment comment=commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		return mapToDTO(comment);
	}
	
	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
		Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		Comment comment=commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
		}
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		
		Comment updatedComment=commentRepository.save(comment);
		
		return mapToDTO(updatedComment);
	}
	
	@Override
	public void deleteComment(Long postId, Long commentId) {
		Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		Comment comment=commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
		}
		commentRepository.delete(comment);
		
	}
	
	//convert ENTITY into DTO
	private CommentDto mapToDTO(Comment comment) {
		
		CommentDto commentDto=modelMapper.map(comment, CommentDto.class) ;
		
//		CommentDto commentDto= new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		return commentDto;
	}
	//convert DTO into ENTITY 
	private Comment mapToEntity(CommentDto commentDto) {
		
		Comment comment= modelMapper.map(commentDto, Comment.class);
		
//		Comment comment=new Comment();
//		comment.setId(commentDto.getId());
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
		return comment;
	}
	
	
	
	

}
