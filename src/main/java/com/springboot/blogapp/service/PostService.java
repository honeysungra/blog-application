package com.springboot.blogapp.service;


import com.springboot.blogapp.payload.PostDto;
import com.springboot.blogapp.payload.PostResponse;

public interface PostService {
	 PostDto createPost(PostDto postDto);
	 
	 PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	 
	 PostDto getPostById(long id);
	 
	 PostDto updatePost(PostDto postDto,long id);
	 
	 void deletePostById(long id);
}
