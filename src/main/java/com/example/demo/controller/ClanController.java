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
import com.example.demo.exception.GrupoNotFoundException;
import com.example.demo.exception.NoContentException;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.model.Clan;
import com.example.demo.model.User;
import com.example.demo.services.ClanService;
import com.example.demo.services.UserService;

@RestController
public class ClanController {
	
	@Autowired private ClanService serviceClan;
	
	@Autowired private UserService serviceUsu;
	
	@GetMapping("/clanes")
	public List<Clan> findAll(){
		List<Clan> findall = serviceClan.findAll();
		
		if (findall.size()==0) {
			throw new NoContentException();
		}
		
		return findall;
	}
	
	@GetMapping("/clanes/{idClan}")
	public Clan findById(@PathVariable int idClan) {
		Clan clanBBDD = serviceClan.findById(idClan);
		
		if(clanBBDD==null) {
			throw new GrupoNotFoundException(idClan);
		}
		
		
		return clanBBDD;
	}
	
	@GetMapping("/usuarios/{idUsu}/clanes/{idClan}")
	public Clan addUsuario(@PathVariable int idClan, @PathVariable Long idUsu) {
		Clan clanBBDD = serviceClan.findById(idClan);
		
		if(clanBBDD==null) {
			throw new GrupoNotFoundException(idClan);
		}
		
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		clanBBDD.getMiembros().add(usuBBDD);
		serviceClan.save(clanBBDD);
		
		return clanBBDD;
	}
	
	@PostMapping("/usuarios/{idUsu}/clanes")
	public Clan newGrupo(@PathVariable Long idUsu, @RequestBody Clan grupo) {
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		Clan grupoAux = new Clan(grupo.getTitulo(), grupo.getDescripcion());
		
		grupoAux.getMiembros().add(usuBBDD);
		
		serviceClan.save(grupoAux);
		
		return grupoAux;
	}
	
	@PostMapping("/usuarios/{idUsu}/clanes/{idClan}")
	public Clan delUsuario(@PathVariable int idClan, @PathVariable Long idUsu) {
		Clan clanBBDD = serviceClan.findById(idClan);
		
		if(clanBBDD==null) {
			throw new GrupoNotFoundException(idClan);
		}
		
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		clanBBDD.getMiembros().remove(usuBBDD);
		usuBBDD.setClan(null);
		serviceClan.save(clanBBDD);
		
		return clanBBDD;
	}
	
	@PutMapping("/clanes/{idClan}")
	public Clan changeGrupo(@PathVariable int idClan, @RequestBody Clan grupo) {
		Clan clanBBDD = serviceClan.findById(idClan);
		
		if(clanBBDD==null) {
			throw new GrupoNotFoundException(idClan);
		}
		
		clanBBDD.setTitulo(grupo.getTitulo());
		clanBBDD.setDescripcion(grupo.getDescripcion());
		serviceClan.save(clanBBDD);
		
		return clanBBDD;
	}
	
	@DeleteMapping("/clanes/{idClan}")
	public void delGrupo(@PathVariable int idClan) {
		Clan grupoBBDD = serviceClan.findById(idClan);
		
		if(grupoBBDD==null) {
			throw new GrupoNotFoundException(idClan);
		}
		
		serviceClan.delete(grupoBBDD);
		
		throw new NoContentException();
	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> UsuarioNotFoundException(UsuarioNotFoundException usuarioException) {
		ApiError apiError = new ApiError(usuarioException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(GrupoNotFoundException.class)
	public ResponseEntity<ApiError> GrupoNotFoundException(GrupoNotFoundException grupoException) {
		ApiError apiError = new ApiError(grupoException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<ApiError> NoContentException(NoContentException noContent) {
		ApiError apiError = new ApiError(noContent.getMessage());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiError);
	}

}
