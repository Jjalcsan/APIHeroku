package com.example.demo.exception;

public class TorneoNotFoundException extends RuntimeException{
	
	static final long serialVersionUID = 5L;

	public TorneoNotFoundException(int id){
		
		super("No hay ningun torneo con ID: "+id+"");
		
	}

}
