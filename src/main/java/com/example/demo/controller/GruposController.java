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
import com.example.demo.model.Grupo;
import com.example.demo.model.Usuario;
import com.example.demo.services.GrupoService;
import com.example.demo.services.UsuarioService;

@RestController
public class GruposController {
	
	@Autowired
	private GrupoService serviceGrup;
	
	@Autowired
	private UsuarioService serviceUsu;
	
	@GetMapping("/grupos")
	public List<Grupo> findAll(){
		List<Grupo> findall = serviceGrup.findAll();
		
		if (findall.size()==0) {
			throw new NoContentException();
		}
		
		return findall;
	}
	
	@GetMapping("/grupos/{idGrup}")
	public Grupo findById(@PathVariable int idGrup) {
		Grupo grupoBBDD = serviceGrup.findById(idGrup);
		
		if(grupoBBDD==null) {
			throw new GrupoNotFoundException(idGrup);
		}
		
		
		return grupoBBDD;
	}
	
	@GetMapping("/usuarios/{idUsu}/grupos/{idGrup}")
	public Grupo addUsuario(@PathVariable int idGrup, @PathVariable String idUsu) {
		Grupo grupoBBDD = serviceGrup.findById(idGrup);
		
		if(grupoBBDD==null) {
			throw new GrupoNotFoundException(idGrup);
		}
		
		Usuario usuBBDD = serviceUsu.findById(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		grupoBBDD.getUsuarios().add(usuBBDD);
		serviceGrup.save(grupoBBDD);
		
		return grupoBBDD;
	}
	
	@PostMapping("/usuarios/{idUsu}/grupos")
	public Grupo newGrupo(@PathVariable String idUsu, @RequestBody Grupo grupo) {
		Usuario usuBBDD = serviceUsu.findById(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		grupo.getUsuarios().add(usuBBDD);
		serviceGrup.save(grupo);
		
		return grupo;
	}
	
	@PostMapping("/usuarios/{idUsu}/grupos/{idGrup}")
	public Grupo delUsuario(@PathVariable int idGrup, @PathVariable String idUsu) {
		Grupo grupoBBDD = serviceGrup.findById(idGrup);
		
		if(grupoBBDD==null) {
			throw new GrupoNotFoundException(idGrup);
		}
		
		Usuario usuBBDD = serviceUsu.findById(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		grupoBBDD.getUsuarios().remove(usuBBDD);
		serviceGrup.save(grupoBBDD);
		
		return grupoBBDD;
	}
	
	@PutMapping("/grupos/{idGrup}")
	public Grupo changeGrupo(@PathVariable int idGrup, @RequestBody Grupo grupo) {
		Grupo grupoBBDD = serviceGrup.findById(idGrup);
		
		if(grupoBBDD==null) {
			throw new GrupoNotFoundException(idGrup);
		}
		
		grupoBBDD.setTitulo(grupo.getTitulo());
		grupoBBDD.setDescripcion(grupo.getDescripcion());
		serviceGrup.save(grupoBBDD);
		
		return grupoBBDD;
	}
	
	@DeleteMapping("/grupos/{idGrup}")
	public void delGrupo(@PathVariable int idGrup) {
		Grupo grupoBBDD = serviceGrup.findById(idGrup);
		
		if(grupoBBDD==null) {
			throw new GrupoNotFoundException(idGrup);
		}
		
		serviceGrup.delete(grupoBBDD);
		
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
