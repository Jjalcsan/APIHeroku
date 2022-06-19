package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Clan {
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Propiedades
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	
	private String descripcion;
	
	@OneToMany(cascade=CascadeType.DETACH)
	private List<User> miembros;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Constructores
	
	public Clan () {
		this.miembros = new ArrayList<User>();
	}
	
	public Clan (String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.miembros = new ArrayList<User>();
	}
	
	public Clan (String titulo, String descripcion, List<User> miembros) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.miembros = miembros;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Setters y Getters
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<User> getMiembros() {
		return miembros;
	}

	public void setMiembros(List<User> usuarios) {
		this.miembros = usuarios;
	}

	public Integer getId() {
		return id;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	//Metodos Override	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clan other = (Clan) obj;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
	}

}
