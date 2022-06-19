package com.example.demo.exception;

public class FotoNotFoundException extends RuntimeException{
	
	static final long serialVersionUID = 1L;

	public FotoNotFoundException(int id){
		
		super("No hay ninguna foto con ID: "+id+"");
		
	}
}
