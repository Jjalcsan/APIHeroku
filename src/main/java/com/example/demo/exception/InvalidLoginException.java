package com.example.demo.exception;

public class InvalidLoginException extends RuntimeException{
	
	static final long serialVersionUID = 1L;

	public InvalidLoginException(){
		
		super("Usuario o contraseña incorrectos");
		
	}
}
