package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Clan;
import com.example.demo.model.Torneo;
import com.example.demo.model.User;
import com.example.demo.services.ClanService;
import com.example.demo.services.TorneoService;
import com.example.demo.services.UserService;
import com.example.demo.exception.*;

@RestController
public class UserController {
    
	@Autowired private UserService serviceUsu;
	
	@Autowired private TorneoService serviceTorn;
	
	@Autowired private ClanService serviceClan;
	
	@GetMapping("/usuarios/{idUsu}")
	public User getUserDetails(@PathVariable Long idUsu){		
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		return usuBBDD;	
	}
	
	@GetMapping("/usuarios")
	public List<User> registrados() {		
		List<User> findall = serviceUsu.findAllUser();		
		
		if (findall.size()==0) {
			throw new NoContentException();
		}
		
		return findall;
	}
	
	@PutMapping("/usuarios/{idUsu}")
	public User editarInfo(@PathVariable Long idUsu, @RequestBody User usuario) {
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		usuBBDD.setNick(usuario.getNick());
		usuBBDD.setPassword(usuario.getPassword());
		usuBBDD.setNombre(usuario.getNombre());
		usuBBDD.setApellidos(usuario.getApellidos());
		usuBBDD.setEmail(usuario.getEmail());
		usuBBDD.setDireccion(usuario.getDireccion());
		usuBBDD.setTelefono(usuario.getTelefono());
		usuBBDD.setEdad(usuario.getEdad());
		usuBBDD.setFotoPerfil(usuario.getFotoPerfil());
		serviceUsu.saveUser(usuBBDD);
		
		return usuBBDD;
	}
	
	@DeleteMapping("/usuarios/{idUsu}")
	public void borrarUsuario(@PathVariable Long idUsu) {
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		List<Clan> findClanes = serviceClan.findAll();
		
		for(Clan c : findClanes) {
			if (c.getMiembros().contains(usuBBDD)) {
				c.getMiembros().remove(usuBBDD);
			}
		}
		
		List<Torneo> findTorneos = serviceTorn.findAll();
		
		for(Torneo t : findTorneos) {
			if (t.getParticipantes().contains(usuBBDD)) {
				t.getParticipantes().remove(usuBBDD);
			}
		}
		
		serviceUsu.deleteUser(usuBBDD);
		
		throw new NoContentException();
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


