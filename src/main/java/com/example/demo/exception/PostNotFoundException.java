package com.example.demo.exception;

public class PostNotFoundException extends RuntimeException{
	
	static final long serialVersionUID = 5L;

	public PostNotFoundException(int id){
		
		super("No hay ningun post con ID: "+id+"");
		
	}
}
