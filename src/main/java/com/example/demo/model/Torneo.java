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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Torneo {
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Propiedades
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private String titulo;
		
	private String descripcion;
	
	@JsonIgnore
	private String premio;
		
	@OneToMany(cascade=CascadeType.DETACH)
	private List<User> participantes;
	
	
	public Torneo() {
		this.participantes = new ArrayList<User>();
	}
	
	public Torneo(String titulo, String descripcion, String premio) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.premio = premio;
		this.participantes = new ArrayList<User>();
	}


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


	public String getPremio() {
		return premio;
	}


	public void setPremio(String premio) {
		this.premio = premio;
	}


	public List<User> getParticipantes() {
		return participantes;
	}


	public void setParticipantes(List<User> participantes) {
		this.participantes = participantes;
	}


	public Integer getId() {
		return id;
	}


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
		Torneo other = (Torneo) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
