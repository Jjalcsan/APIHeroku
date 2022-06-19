package com.example.demo.exception;

public class GrupoNotFoundException extends RuntimeException{
	
	static final long serialVersionUID = 4L;

	public GrupoNotFoundException(int id){
		
		super("No hay ningun grupo con ID: "+id+"");
		
	}
}
