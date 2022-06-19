package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Propiedades
	
	@Id
	private String nick;
	
	@Column(name = "contra", nullable = false)
	private String contra;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "apellidos", nullable = false)
	private String apellidos;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;
	
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	@Column(name = "edad", nullable = false)
	private Integer edad;
	
	@Column(name = "foto")
	private String fotoPerfil;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Album> albumes;
	
	@OneToMany(cascade = CascadeType.DETACH, orphanRemoval = true)
	private List<Usuario> seguidores;
	
	@OneToMany(cascade = CascadeType.DETACH, orphanRemoval = true)
	private List<Usuario> seguidos;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> posts;

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Constructores
	
	/**
	 * Constructor vacio
	 */
	public Usuario() {	
		this.albumes = new ArrayList<>();
		this.seguidores = new ArrayList<>();
		this.seguidos = new ArrayList<>();
		//this.posts = new ArrayList<>();		
	}
	
	/**
	 * Constructor que solo contiene el email y la contraseña
	 * @param email
	 * @param contra
	 */
	public Usuario(String email, String contra) {
		this.email = email;
		this.contra = contra;
		this.albumes = new ArrayList<>();
		this.seguidores = new ArrayList<>();
		this.seguidos = new ArrayList<>();
		//this.posts = new ArrayList<>();
	}
	
	/**
	 * Constructor con todos los parametros menos las listas que las inicializa vacias
	 * @param nick
	 * @param contra
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param direccion
	 * @param telefono
	 * @param edad
	 */
	public Usuario(String nick, String contra, String nombre, String apellidos, String email, String direccion, String telefono, Integer edad, String fotoPerfil) {
		this.nick = nick;
		this.contra = contra;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.edad = edad;
		this.fotoPerfil = fotoPerfil;
		this.albumes = new ArrayList<>();
		this.seguidores = new ArrayList<>();
		this.seguidos = new ArrayList<>();
		//this.posts = new ArrayList<>();
	}
	
	/**
	 * Constructor con todos los parametros incluido las listas que las recibe como argumento
	 * @param nick
	 * @param contra
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param direccion
	 * @param telefono
	 * @param edad
	 * @param albumes
	 * @param seguidores
	 * @param seguidos
	 * @param posts
	 */
	public Usuario(String nick, String contra, String nombre, String apellidos, String email, String direccion, String telefono, Integer edad, String fotoPerfil, List<Album> albumes, List<Usuario> seguidores, List<Usuario> seguidos, List<Post> posts) {
		this.nick = nick;
		this.contra = contra;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.edad = edad;
		this.fotoPerfil = fotoPerfil;
		this.albumes = albumes;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
		this.posts = posts;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Setters y Getters

	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getContra() {
		return contra;
	}


	public void setContra(String contra) {
		this.contra = contra;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public List<Album> getAlbumes() {
		return albumes;
	}


	public void setAlbumes(List<Album> albumes) {
		this.albumes = albumes;
	}


	public List<Usuario> getSeguidores() {
		return seguidores;
	}


	public void setSeguidores(List<Usuario> seguidores) {
		this.seguidores = seguidores;
	}


	public List<Usuario> getSeguidos() {
		return seguidos;
	}


	public void setSeguidos(List<Usuario> seguidos) {
		this.seguidos = seguidos;
	}


	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	//Metodos Override	

	@Override
	public int hashCode() {
		return Objects.hash(nick);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nick, other.nick);
	}
	
}
