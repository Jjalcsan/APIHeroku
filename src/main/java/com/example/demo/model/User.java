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
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * La entidad principal de nuestra aplicación: User
 * El usuario es el que navegará por la aplicación creando o borrando posts o grupos y siguiendo o dejando de seguir a otros usuarios
 * Esta entidad esta identificada por su ID que se genera automaticamente y tendra mas campos como email y contraseña que la utilizará para realizar login
 * Un nombre y unos apellidos, una dirección, un teléfono, una edad, una ruta a una foto de perfil asi como los Arrays de albumes, posts, seguidores y seguidos
 * que se inicializan vacíos y se irán rellenando conforme hagamos operaciones en la aplicación
 * @author Juan Jose
 *
 */
@Entity
public class User {

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Propiedades
	
	//private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	private String nick;
	
	private String nombre;
	
	private String apellidos;
	
	private String direccion;
	
	private String telefono;
	
	private Integer edad;
	
	private String fotoPerfil;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Album> albumes;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Post> posts;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JsonIgnore
	private Clan clan;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JsonIgnore
	private Torneo torneo;
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Constructores
	
	public User(){
		this.albumes = new ArrayList<Album>();
		this.posts = new ArrayList<Post>();
	}; 
	
	
	public User(String email, String password, String nick, String nombre, String apellidos, String direccion, String telefono, Integer edad, String fotoPerfil) {	
		this.email = email;
		this.password = password;
		this.nick = nick;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.edad = edad;
		this.fotoPerfil = fotoPerfil;
		this.albumes = new ArrayList<Album>();
		this.posts = new ArrayList<Post>();
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Setters y Getters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
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
	
	public String getFotoPerfil() {
		return fotoPerfil;
	}


	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}


	public List<Album> getAlbumes() {
		return albumes;
	}


	public void setAlbumes(List<Album> albumes) {
		this.albumes = albumes;
	}

	
	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public Clan getClan() {
		return clan;
	}


	public void setClan(Clan clan) {
		this.clan = clan;
	}
	

	public Torneo getTorneo() {
		return torneo;
	}


	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
		//Metodos Override
	
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
		User other = (User) obj;
		return id == other.id;
	}

}