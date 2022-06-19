package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {

	@Autowired private UserRepo repoUsu;
	
	public User findByEmail(String email) {
		return repoUsu.findByEmail(email).orElse(null);
	}
	
	public User findByIdUser(Long id) {
		return repoUsu.findById(id).orElse(null);
	}
	
	public List<User> findAllUser(){
		return repoUsu.findAll();
	}
	
	public void saveUser(User usuario) {
		repoUsu.save(usuario);
	}
	
	public void deleteUser(User usuario) {
		repoUsu.delete(usuario);
	}
	
	public User saveReturnUser(User usuario) {
		return repoUsu.save(usuario);
	}
	
}
