package com.example.demo.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JWTUtil;
import com.example.demo.services.UsuarioService;
import com.example.demo.model.Usuario;
import com.example.demo.exception.ApiError;
import com.example.demo.exception.InvalidLoginException;
import com.example.demo.exception.UsuarioNotFoundException;
import com.example.demo.model.LoginCredentials;

@RestController
public class AuthController {
	
	@Autowired
	private UsuarioService serviceUsu;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            throw new InvalidLoginException();
        }
    }
    
    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody Usuario user){
        String encodedPass = passwordEncoder.encode(user.getContra());
        user.setContra(encodedPass);
        user = serviceUsu.saveReturn(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return Collections.singletonMap("jwt-token", token);
    }
    
    @GetMapping("/mail")
    public boolean existeEmail(@RequestParam String email) {
    	boolean existe = false;
    	
    	for (Usuario u : serviceUsu.findAll()) {
    		if(u.getEmail().equals(email)){
    			existe = true;
    		}
    	}
    	
    	return existe;
    }
    
    @GetMapping("/nick")
    public boolean existeNick(@RequestParam String nick) {
    	boolean existe = false;
    	
    	for(Usuario u : serviceUsu.findAll()) {
    		if(u.getNick().equals(nick)) {
    			existe = true;
    		}
    	}
    	
    	return existe;    	
    }
    
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ApiError> UsuarioNotFoundException(UsuarioNotFoundException usuarioException) {
		ApiError apiError = new ApiError(usuarioException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

}
