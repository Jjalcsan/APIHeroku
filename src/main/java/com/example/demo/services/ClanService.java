package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Clan;
import com.example.demo.repository.ClanRepository;

@Service
public class ClanService {

	@Autowired
	private ClanRepository repoGrup;
	
	public Clan findById(int id) {
		return repoGrup.findById(id).orElse(null);
	}
	
	public List<Clan> findAll(){
		return repoGrup.findAll();
	}
	
	public void save(Clan grupo) {
		repoGrup.save(grupo);
	}
	
	public void delete(Clan grupo) {
		repoGrup.delete(grupo);
	}
}
