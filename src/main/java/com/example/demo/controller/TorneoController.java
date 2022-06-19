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
import com.example.demo.exception.TorneoNotFoundException;
import com.example.demo.exception.NoContentException;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.model.Torneo;
import com.example.demo.model.User;
import com.example.demo.services.TorneoService;
import com.example.demo.services.UserService;

@RestController
public class TorneoController {
	
	@Autowired private TorneoService serviceTorn;
	
	@Autowired private UserService serviceUsu;
	
	@GetMapping("/torneos")
	public List<Torneo> findAll(){
		List<Torneo> findall = serviceTorn.findAll();
		
		if (findall.size()==0) {
			throw new NoContentException();
		}
		
		return findall;
	}
	
	@GetMapping("/torneos/{idTorn}")
	public Torneo findById(@PathVariable int idTorn) {
		Torneo torneoBBDD = serviceTorn.findById(idTorn);
		
		if(torneoBBDD==null) {
			throw new TorneoNotFoundException(idTorn);
		}
		
		
		return torneoBBDD;
	}
	
	@GetMapping("/usuarios/{idUsu}/torneos/{idTorn}")
	public Torneo addUsuario(@PathVariable int idTorn, @PathVariable Long idUsu) {
		Torneo torneoBBDD = serviceTorn.findById(idTorn);
		
		if(torneoBBDD==null) {
			throw new TorneoNotFoundException(idTorn);
		}
		
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		torneoBBDD.getParticipantes().add(usuBBDD);
		serviceTorn.save(torneoBBDD);
		
		return torneoBBDD;
	}
	
	@PostMapping("/usuarios/{idUsu}/torneos")
	public Torneo newGrupo(@PathVariable Long idUsu, @RequestBody Torneo torneo) {
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		Torneo torneoAux = new Torneo(torneo.getTitulo(), torneo.getDescripcion(), torneo.getPremio());
		
		torneoAux.getParticipantes().add(usuBBDD);
		
		serviceTorn.save(torneoAux);
		
		return torneoAux;
	}
	
	@PostMapping("/usuarios/{idUsu}/torneos/{idTorn}")
	public Torneo delUsuario(@PathVariable int idTorn, @PathVariable Long idUsu) {
		Torneo torneoBBDD = serviceTorn.findById(idTorn);
		
		if(torneoBBDD==null) {
			throw new TorneoNotFoundException(idTorn);
		}
		
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		torneoBBDD.getParticipantes().remove(usuBBDD);
		usuBBDD.setClan(null);
		serviceTorn.save(torneoBBDD);
		
		return torneoBBDD;
	}
	
	@PutMapping("/torneos/{idTorn}")
	public Torneo changeGrupo(@PathVariable int idTorn, @RequestBody Torneo grupo) {
		Torneo torneoBBDD = serviceTorn.findById(idTorn);
		
		if(torneoBBDD==null) {
			throw new TorneoNotFoundException(idTorn);
		}
		
		torneoBBDD.setTitulo(grupo.getTitulo());
		torneoBBDD.setDescripcion(grupo.getDescripcion());
		serviceTorn.save(torneoBBDD);
		
		return torneoBBDD;
	}
	
	@DeleteMapping("/torneos/{idTorn}")
	public void delGrupo(@PathVariable int idTorn) {
		Torneo torneoBBDD = serviceTorn.findById(idTorn);
		
		if(torneoBBDD==null) {
			throw new TorneoNotFoundException(idTorn);
		}
		
		serviceTorn.delete(torneoBBDD);
		
		throw new NoContentException();
	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> UsuarioNotFoundException(UsuarioNotFoundException usuarioException) {
		ApiError apiError = new ApiError(usuarioException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(TorneoNotFoundException.class)
	public ResponseEntity<ApiError> TorneoNotFoundException(TorneoNotFoundException torneoException) {
		ApiError apiError = new ApiError(torneoException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<ApiError> NoContentException(NoContentException noContent) {
		ApiError apiError = new ApiError(noContent.getMessage());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiError);
	}

}
