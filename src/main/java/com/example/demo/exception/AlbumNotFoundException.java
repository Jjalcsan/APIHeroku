package com.example.demo.exception;

public class AlbumNotFoundException extends RuntimeException{
	
	static final long serialVersionUID = 6L;

	public AlbumNotFoundException(int id){
		
		super("No hay ningun album con ID: "+id+"");
		
	}
	
}
