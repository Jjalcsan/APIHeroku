package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.GrupoNotFoundException;
import com.example.demo.exception.NoContentException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.Usuario;
import com.example.demo.services.PostService;
import com.example.demo.services.UsuarioService;

@RestController
public class PostsController {
	
	@Autowired private UsuarioService serviceUsu;
	
	@Autowired private PostService servicePost;
	
	@GetMapping("/posts")
	public List<Post> getAllPosts(){
		List<Post> findall = servicePost.findAll();
		
		if (findall.size()==0) {
			throw new NoContentException();
		}
		
		return findall;
	}
	
	@GetMapping("usuarios/{idUsu}/posts")
	public List<Post> comentariosUsuario(@PathVariable String idUsu){
		Usuario usuBBDD = serviceUsu.findById(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		return usuBBDD.getPosts();
	}
	
	@PostMapping("usuarios/{idUsu}/posts")
	public Post newPost(@PathVariable String idUsu, @RequestBody Post post) {
		Usuario usuBBDD = serviceUsu.findById(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		Post postBBDD = new Post(post.getContenido());
		servicePost.save(postBBDD);
		usuBBDD.getPosts().add(postBBDD);
		serviceUsu.save(usuBBDD);
		
		return post;
	}
	
	@DeleteMapping("usuarios/{idUsu}/posts/{idPost}")
	public void deletePost(@PathVariable String idUsu, @PathVariable int idPost) {
		Usuario usuBBDD = serviceUsu.findById(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		Post postBBDD = servicePost.findById(idPost);
		
		if(postBBDD==null) {
			throw new PostNotFoundException(idPost);
		}
		
		usuBBDD.getPosts().remove(postBBDD);
		serviceUsu.save(usuBBDD);
		servicePost.delete(postBBDD);
		
		throw new NoContentException();
	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> UsuarioNotFoundException(UsuarioNotFoundException usuarioException) {
		ApiError apiError = new ApiError(usuarioException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(GrupoNotFoundException.class)
	public ResponseEntity<ApiError> FotoNotFoundException(GrupoNotFoundException grupoException) {
		ApiError apiError = new ApiError(grupoException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<ApiError> NoContentException(NoContentException noContent) {
		ApiError apiError = new ApiError(noContent.getMessage());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiError);
	}
	
}
