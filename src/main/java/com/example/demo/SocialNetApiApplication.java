package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

@SpringBootApplication
public class SocialNetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetApiApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initU (UserRepo repoUsu, PasswordEncoder pass) {
		
		return(args) -> {
			repoUsu.saveAll(Arrays.asList(
					new User(
							 "admin@admin.com", 											//Nick
							 pass.encode("admin"),
							 "admin",
							 "admin",
							 "admin",
							 "admin",
							 "admin",
							 22,
							 "../../imgs"),											//Ruta a la foto de perfil en los assets del front
					
					new User(
							 "admin@admin.com2", 											//Nick
							 pass.encode("admin2"),
							 "admin2",
							 "admin2",
							 "admin2",
							 "admin2",
							 "admin2",
							 22,
							 "../../imgs"),											//Ruta a la foto de perfil en los assets del front
					
					new User(
							 "admin@admin.com3", 											//Nick
							 pass.encode("admin3"),
							 "admin3",
							 "admin3",
							 "admin3",
							 "admin3",
							 "admin3",
							 22,
							 "../../imgs")));
		};
		
	}


}
