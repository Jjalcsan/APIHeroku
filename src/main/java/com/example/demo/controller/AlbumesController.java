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
import com.example.demo.model.Album;
import com.example.demo.model.Foto;
import com.example.demo.model.User;
import com.example.demo.services.AlbumService;
import com.example.demo.services.UserService;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.exception.NoContentException;
import com.example.demo.exception.AlbumNotFoundException;
import com.example.demo.exception.FotoNotFoundException;

@RestController
public class AlbumesController {
	
	@Autowired
	private AlbumService serviceAlb;
	
	@Autowired
	private UserService serviceUsu;
	
	@GetMapping("/albumes")
	public List<Album> findAll(){
		List<Album> findall = serviceAlb.findAllAlb();
		
		if (findall.size()==0) {
			throw new NoContentException();
		}
		
		return findall;
		
	}
	
	@GetMapping("/albumes/{idAlb}")
	public Album findAlbumById(@PathVariable int idAlb) {
		Album albBBDD = serviceAlb.findByIdAlb(idAlb);
		
		if(albBBDD==null) {
			throw new AlbumNotFoundException(idAlb);
		}
		
		 return albBBDD;
	}
	
	@PostMapping("/usuarios/{idUsu}/albumes")
	public Album newAlbum(@PathVariable Long idUsu, @RequestBody Album album) {
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		Album albumAux = new Album(album.getNombre(), usuBBDD);
		usuBBDD.getAlbumes().add(albumAux);
		serviceUsu.saveUser(usuBBDD);
		
		return album;
	}
	
	@PutMapping("/albumes/{idAlb}")
	public Album newNombreAlbum(@PathVariable int idAlb, @RequestBody Album album) {
		Album albBBDD = serviceAlb.findByIdAlb(idAlb);
		
		if(albBBDD==null) {
			throw new AlbumNotFoundException(idAlb);
		}
		
		albBBDD.setNombre(album.getNombre());
		serviceAlb.saveAlb(albBBDD);
		
		return albBBDD;
	}
	
	@DeleteMapping("/usuarios/{idUsu}/albumes/{idAlb}")
	public void deleteAlbum(@PathVariable int idAlb, @PathVariable Long idUsu) {
		User usuBBDD = serviceUsu.findByIdUser(idUsu);
		
		if(usuBBDD==null) {
			throw new UsuarioNotFoundException(idUsu);
		}
		
		Album albBBDD = serviceAlb.findByIdAlb(idAlb);
		
		if(albBBDD==null) {
			throw new AlbumNotFoundException(idAlb);
		}
		
		usuBBDD.getAlbumes().remove(albBBDD);
		serviceUsu.saveUser(usuBBDD);
		serviceAlb.deleteAlb(albBBDD);
		
		throw new NoContentException();
	}
	
	@GetMapping("/albumes/{idAlb}/fotos")
	public List<Foto> findAllOfAlbum(@PathVariable int idAlb) {
		Album albBBDD = serviceAlb.findByIdAlb(idAlb);
		
		if(albBBDD==null) {
			throw new AlbumNotFoundException(idAlb);
		}
		
		if(albBBDD.getFotos().size()==0) {
			throw new NoContentException();
		}
		
		return albBBDD.getFotos();
	}
	
	@GetMapping("/albumes/{idAlb}/fotos/{idFot}")
	public Foto findFotoById(@PathVariable int idFot, @PathVariable int idAlb) {
		Album albBBDD = serviceAlb.findByIdAlb(idAlb);
		
		if(albBBDD==null) {
			throw new AlbumNotFoundException(idAlb);
		}
		
		Foto fotoBBDD = serviceAlb.findByIdFot(idFot);
		
		if(fotoBBDD==null) {
			throw new FotoNotFoundException(idFot);
		}
		
		return fotoBBDD;
	}
	
	@PostMapping("/albumes/{idAlb}/fotos")
	public Foto newFoto(@PathVariable int idAlb, @RequestBody Foto foto) {
		Album albBBDD = serviceAlb.findByIdAlb(idAlb);
		
		if(albBBDD==null) {
			throw new AlbumNotFoundException(idAlb);
		}
		
		Foto fotoAux = new Foto(foto.getTitulo(), albBBDD, foto.getRuta());
		serviceAlb.saveFot(fotoAux);
		albBBDD.getFotos().add(fotoAux);
		serviceAlb.saveAlb(albBBDD);
		
		return foto;
	}
	
	@DeleteMapping("/albumes/{idAlb}/fotos/{idFot}")
	public void deleteFoto(@PathVariable int idAlb, @PathVariable int idFot) {
		Album albBBDD = serviceAlb.findByIdAlb(idAlb);
		
		if(albBBDD==null) {
			throw new AlbumNotFoundException(idAlb);
		}
		
		Foto fotoBBDD = serviceAlb.findByIdFot(idFot);
		
		if(fotoBBDD==null) {
			throw new FotoNotFoundException(idFot);
		}
		
		albBBDD.getFotos().remove(fotoBBDD);
		serviceAlb.saveAlb(albBBDD);
		serviceAlb.deleteFot(fotoBBDD);
		
		throw new NoContentException();
	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> UsuarioNotFoundException(UsuarioNotFoundException usuarioException) {
		ApiError apiError = new ApiError(usuarioException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(AlbumNotFoundException.class)
	public ResponseEntity<ApiError> AlbumNotFoundException(AlbumNotFoundException albumException) {
		ApiError apiError = new ApiError(albumException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(FotoNotFoundException.class)
	public ResponseEntity<ApiError> FotoNotFoundException(FotoNotFoundException fotoException) {
		ApiError apiError = new ApiError(fotoException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<ApiError> NoContentException(NoContentException noContent) {
		ApiError apiError = new ApiError(noContent.getMessage());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiError);
	}
	
	

}
