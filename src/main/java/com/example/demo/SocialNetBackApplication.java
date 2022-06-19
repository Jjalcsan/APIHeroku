package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@SpringBootApplication
public class SocialNetBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetBackApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initU (UsuarioRepository repoUsu, PasswordEncoder pass) {
		
		return(args) -> {
			repoUsu.saveAll(Arrays.asList(
					new Usuario(
							 "admin", 											//Nick
							 pass.encode("admin"), 											//Contraseña sin encriptar
							 "Administrador", 									//Nombre
							 "Administrando", 									//Apellidos
							 "admin@admin.com", 								//Email
							 "C/Administrados Nº1",								//Direccion
							 "123456789", 										//Telefono
							 30,												//Edad
							 "path"),											//Ruta a la foto de perfil en los assets del front
					
					new Usuario(
							 "norwood1986", 									//Nick
							 pass.encode("norwood1986"), 									//Contraseña sin encriptar
							 "Richard", 										//Nombre
							 "R Aguirre", 										//Apellidos
							 "norwood1986@yahoo.com", 							//Email
							 "4331 Concord Street, North Carolina(NC)",			//Direccion
							 "704-519-9748", 									//Telefono
							 36 ,												//Edad
							 "path"),											//Ruta a la foto de perfil en los assets del front
					
					new Usuario(
							 "phoebe_grad4", 									//Nick
							 pass.encode("phoebe_grad4"), 									//Contraseña sin encriptar
							 "Catherine", 										//Nombre
							 "K Carden", 										//Apellidos
							 "phoebe_grad4@gmail.com", 							//Email
							 "4304 Sundown Lane, Texas(TX)",					//Direccion
							 "817-675-5039", 									//Telefono
							 38,												//Edad
							 "path"),											//Ruta a la foto de perfil en los assets del front
					
					new Usuario(
							 "erin1991", 										//Nick
							 pass.encode("erin1991"), 										//Contraseña sin encriptar
							 "David", 											//Nombre
							 "K Culver", 										//Apellidos
							 "erin1991@gmail.com", 								//Email
							 "2703 Brookview Drive, Texas(TX)",					//Direccion
							 "409-202-9682", 									//Telefono
							 24,												//Edad
							 "path"),											//Ruta a la foto de perfil en los assets del front
					
					new Usuario(
							 "elliot_bauc1", 									//Nick
							 pass.encode("elliot_bauc1"), 									//Contraseña sin encriptar
							 "Rickey", 											//Nombre
							 "M Hammond", 										//Apellidos
							 "elliot_bauc1@gmail.com", 							//Email
							 "1291 Illinois Avenue, Oregon(OR)",				//Direccion
							 "123456789", 										//Telefono
							 63,												//Edad
							 "path")											//Ruta a la foto de perfil en los assets del front
					));
		};
		
	}


}
