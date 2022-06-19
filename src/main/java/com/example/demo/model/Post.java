package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Propiedades
	
	//private static int contador = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String contenido;
	
	private Integer likes;
	
	private static Date fecha = new Date();
	
	private String goodFecha;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Constructores
	
	public Post() {
		this.likes = 0;
		this.goodFecha = getGoodFecha();
	}
	
	public Post(String contenido, User user) {
		this.contenido = contenido;
		this.likes = 0;
		this.user = user;
		this.goodFecha = getGoodFecha();
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Setters y Getters
	
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	// Metodos Override
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	// Metodos adicionales de la clase
	
	
	public String getGoodFecha() {
		return new SimpleDateFormat("dd-MM-yyyy").format(Post.fecha);
	}

}
