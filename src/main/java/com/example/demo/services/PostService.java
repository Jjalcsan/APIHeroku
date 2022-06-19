package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repoPost;
	
	public Post findById(int id) {
		return repoPost.findById(id).orElse(null);
	}
	
	public List<Post> findAll(){
		return repoPost.findAll();
	}
	
	public void save(Post post) {
		repoPost.save(post);
	}
	
	public void delete(Post post) {
		repoPost.delete(post);
	}
}
