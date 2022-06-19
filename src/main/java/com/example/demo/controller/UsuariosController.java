package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.NoContentException;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.model.Usuario;
import com.example.demo.services.UsuarioService;

@RestController
public class UsuariosController {

	@Autowired
	private UsuarioService serviceUsu;
	
	@GetMapping("/usuarios/{idUsu}")
	public Usuario getUserDetails(@PathVariable String idUsu){		
		Usuario usuBBDD = serviceUsu.findById(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		return usuBBDD;	
	}
	
	@GetMapping("/usuarios")
	public List<Usuario> registrados() {		
		List<Usuario> findall = serviceUsu.findAll();		
		
		if (findall.size()==0) {
			throw new NoContentException();
		}
		
		return findall;
	}
	
	@PutMapping("/usuarios/{idUsu}")
	public Usuario editarInfo(@PathVariable String idUsu, @RequestBody Usuario usuario) {
		Usuario usuBBDD = serviceUsu.findById(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		usuBBDD.setContra(usuario.getContra());
		usuBBDD.setNombre(usuario.getNombre());
		usuBBDD.setApellidos(usuario.getApellidos());
		usuBBDD.setEmail(usuario.getEmail());
		usuBBDD.setDireccion(usuario.getDireccion());
		usuBBDD.setTelefono(usuario.getTelefono());
		usuBBDD.setEdad(usuario.getEdad());
		usuBBDD.setFotoPerfil(usuario.getFotoPerfil());
		serviceUsu.save(usuBBDD);
		
		return usuBBDD;
	}
	
	@DeleteMapping("/usuarios/{idUsu}")
	public void borrarUsuario(@PathVariable String idUsu) {
		Usuario usuBBDD = serviceUsu.findById(idUsu);
		
		serviceUsu.delete(usuBBDD);
		
		throw new NoContentException();
	}
	
	@PostMapping("/usuarios/{idUsu}")
	public Usuario seguirUsuario(@PathVariable String idUsu, @RequestBody Usuario usuario) {
		Usuario usuBBDDSeguidor = serviceUsu.findById(idUsu);
		Usuario usuBBDDSeguido = serviceUsu.findById(usuario.getNick());
		
		usuBBDDSeguidor.getSeguidos().add(usuBBDDSeguido);
		usuBBDDSeguido.getSeguidores().add(usuBBDDSeguidor);
		
		serviceUsu.save(usuBBDDSeguidor);
		serviceUsu.save(usuBBDDSeguido);
		
		return usuBBDDSeguido;
	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> UsuarioNotFoundException(UsuarioNotFoundException usuarioException) {
		ApiError apiError = new ApiError(usuarioException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<ApiError> NoContentException(NoContentException noContent) {
		ApiError apiError = new ApiError(noContent.getMessage());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiError);
	}
	
}
