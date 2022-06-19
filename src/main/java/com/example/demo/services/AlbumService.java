package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Album;
import com.example.demo.model.Foto;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.FotoRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository repoAlb;
	
	@Autowired
	private FotoRepository repoFot;
	
	public List<Album> findAllAlb(){
		return repoAlb.findAll();
	}
	
	public Album findByIdAlb(int id) {
		return repoAlb.findById(id).orElse(null);
	}
	
	public void saveAlb(Album album) {
		repoAlb.save(album);
	}
	
	public void deleteAlb(Album album) {
		repoAlb.delete(album);
	}
	
	public List<Foto> findAllFot(){
		return repoFot.findAll();
	}
	
	public Foto findByIdFot(int id) {
		return repoFot.findById(id).orElse(null);
	}
	
	public void saveFot(Foto foto) {
		repoFot.save(foto);
	}
	
	public void deleteFot(Foto foto) {
		repoFot.delete(foto);
	}
	
	
	
}
