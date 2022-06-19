package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Foto {
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Propiedades
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	private String titulo;
	
	private String ruta;
	
	@ManyToOne
	@JsonBackReference
	private Album album;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Constructores
	
	/**
	 * Constructor vacio
	 */
	public Foto() {}
	
	/**
	 * Constructor con el titulo y la ruta de la foto
	 * @param titulo
	 * @param ruta
	 */
	public Foto(String titulo, Album album, String ruta) {
		this.titulo = titulo;
		this.album = album;
		this.ruta = ruta;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Setters y Getters
	
	public Integer getId() {
		return Id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	//Metodos Override
	
	@Override
	public int hashCode() {
		return Objects.hash(Id, ruta, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(ruta, other.ruta) && Objects.equals(titulo, other.titulo);
	}
	
}
