package com.springboot.blogapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blogapp.entity.Post;
import com.springboot.blogapp.exception.ResourceNotFoundException;
import com.springboot.blogapp.payload.PostDto;
import com.springboot.blogapp.payload.PostResponse;
import com.springboot.blogapp.repository.PostRepository;
import com.springboot.blogapp.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	private PostRepository postRepository;
	
	private ModelMapper modelMapper;
	
	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper=modelMapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		//convert DTO to Post
//		Post post=new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		Post post= mapToEntity(postDto);
		
		Post newPost= postRepository.save(post);
		
		//convert Post to DTO
//		PostDto postResponse= new PostDto();
//		postResponse.setId(newPost.getId());
//		postResponse.setTitle(newPost.getTitle());
//		postResponse.setDescription(newPost.getDescription());
//		postResponse.setContent(newPost.getContent());
		
		PostDto postResponse= mapToDTO(newPost);
	
		return postResponse;
		
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDir) {
		Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
		Page<Post> posts= postRepository.findAll(pageable);
		//get Content for page Object
		List<Post> listOfPost= posts.getContent();
		
		List<PostDto> content = listOfPost.stream().map(post-> mapToDTO(post)).collect(Collectors.toList());

		PostResponse postResponse= new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPage(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
		
		
	}
	
	@Override
	public PostDto getPostById(long id) {
		Post post= postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		
		return mapToDTO(post);
	}
	
	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		// TODO Auto-generated method stub
		Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost=postRepository.save(post);
		return mapToDTO(updatedPost);
		
	}
	
	@Override
	public void deletePostById(long id) {
			Post post= postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
			 postRepository.delete(post);
	}

	
	//convert ENTITY into DTO
	private PostDto mapToDTO(Post post) {
		
		PostDto postDto= modelMapper.map(post, PostDto.class);	
//		PostDto postDto=new PostDto();
//		postDto.setId(post.getId());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(post.getDescription());
//		postDto.setContent(post.getContent());
		return postDto;
	}
	//convert DTO To ENTITY
	private Post mapToEntity(PostDto postDto) {
		
		Post post=modelMapper.map(postDto, Post.class);
		
//		Post post=new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		return post;
	}

	
	

	

	
	
}
