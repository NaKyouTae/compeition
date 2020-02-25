package com.competition;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.competition.jpa.model.User;
import com.competition.jpa.repository.UserRepository;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CompetitionServerApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CompetitionServerApplication.class, args);
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
        .allowedOrigins("http://localhost:4300", "http://localhost:8080")
        .allowCredentials(true).maxAge(3600);
    }
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public CommandLineRunner runner(UserRepository userRepository) {
		return (args) -> {
			userRepository.deleteAll();
			
			User u = new User();
			u.setUsername("test");
			u.setPassword(passwordEncoder.encode("test"));
			u.setPrincipal(passwordEncoder.encode("test"));
			u.setRole("ROLE_ADMIN");
			u.setInsert_date(LocalDateTime.now());
			
			userRepository.save(u);
		};
    } 
}
