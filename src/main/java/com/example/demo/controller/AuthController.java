package com.example.demo.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LoginCredentials;
import com.example.demo.model.User;
import com.example.demo.security.JWTUtil;
import com.example.demo.services.UserService;

@RestController
public class AuthController {

    @Autowired private UserService serviceUsu;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody User user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user = serviceUsu.saveReturnUser(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return Collections.singletonMap("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
    
    @GetMapping("/mail")
    public boolean existeEmail(@RequestParam String email) {
    	boolean existe = false;
    	
    	for (User u : serviceUsu.findAllUser()) {
    		if(u.getEmail().equals(email)){
    			existe = true;
    		}
    	}
    	
    	return existe;
    }
    
    @GetMapping("/nick")
    public boolean existeNick(@RequestParam String nick) {
    	boolean existe = false;
    	
    	for(User u : serviceUsu.findAllUser()) {
    		if(u.getNick().equals(nick)) {
    			existe = true;
    		}
    	}
    	
    	return existe;    	
    }


}
