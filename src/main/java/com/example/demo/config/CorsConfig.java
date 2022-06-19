package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*")	
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With", "accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers", "Content type 'text/plain;charset=UTF-8'")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/login")	
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With", "accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers", "Content type 'text/plain;charset=UTF-8'")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/register")	
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With", "accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers", "Content type 'text/plain;charset=UTF-8'")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/usuarios/{idUsu}")	
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With", "accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers", "Content type 'text/plain;charset=UTF-8'")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
			}
			
			
		};
	}


}
