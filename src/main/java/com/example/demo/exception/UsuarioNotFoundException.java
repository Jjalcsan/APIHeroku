package com.example.demo.exception;

public class UsuarioNotFoundException extends RuntimeException{
	
	static final long serialVersionUID = 2L;

	public UsuarioNotFoundException(Long id){
		
		super("No hay ningun usuario con id: "+id+"");
		
	}
}
