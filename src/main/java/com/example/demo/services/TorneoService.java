package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Torneo;
import com.example.demo.repository.TorneoRepository;

@Service
public class TorneoService {
	
	@Autowired private TorneoRepository repoTorn;
	
	public Torneo findById(int id) {
		return repoTorn.findById(id).orElse(null);
	}
	
	public List<Torneo> findAll() {
		return repoTorn.findAll();
	}
	
	public void save(Torneo torneo) {
		repoTorn.save(torneo);
	}
	
	public void delete(Torneo torneo) {
		repoTorn.delete(torneo);
	}

}
