package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Album {
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Propiedades
	
	//private static int contador = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Foto> fotos;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Constructores
	
	/**
	 * Constructor vacio
	 */
	public Album() {
		this.fotos = new ArrayList<Foto>();
	}
	
	public Album(String nombre) {
		this.nombre = nombre;
		this.fotos = new ArrayList<Foto>();
	}
	
	/**
	 * Constructor con el nombre del album, el usuario y una lista de fotos vacia
	 * @param nombre
	 * @param usuario
	 */
	public Album(String nombre, User user) {
		this.nombre = nombre;
		this.user = user;
		this.fotos = new ArrayList<Foto>();
	}
	
	/**
	 * Constructor con el nombre del album, el usuario y una lista de fotos
	 * @param nombre
	 * @param fotos
	 * @param usuario
	 */
	public Album(String nombre, User user, List<Foto> fotos) {
		this.nombre = nombre;
		this.user = user;
		this.fotos = fotos;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Setters y Getters
	
	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	//Metodos Override
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		return Objects.equals(nombre, other.nombre);
	}

}
