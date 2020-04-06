package com.competition;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.competition.jpa.model.Role;
import com.competition.jpa.model.User;
import com.competition.jpa.model.UserMappingRole;
import com.competition.jpa.repository.MenuRepository;
import com.competition.jpa.repository.RoleRepository;
import com.competition.jpa.repository.UserMappingRoleRepository;
import com.competition.jpa.repository.UserRepository;
import com.competition.util.DateUtil;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CompetitionServerApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CompetitionServerApplication.class, args);
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
        .allowedOrigins("http://localhost:4300", "http://localhost:8080")
        .allowedMethods("GET","POST", "PUT", "DELETE")
        .allowCredentials(true).maxAge(3600);
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new JwtInterceptor())
//				.addPathPatterns("/service/**")
//				.excludePathPatterns("/user/**");
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public CommandLineRunner runner(UserRepository user, UserMappingRoleRepository mapping_role, RoleRepository role, MenuRepository menu) {
		return (args) -> {
			{
				user.deleteAll();
				
				User u = new User();
				u.setIdx(UUID.randomUUID().toString());
				u.setUsername("admin");
				u.setPw(passwordEncoder.encode("skrbxo12!@"));
				u.setInsert_date(DateUtil.now());
				u.setChange_date(null);
				user.save(u);
			}
			
			{
				role.deleteAll();
				
				Role admin_role = new Role();
				admin_role.setIdx(UUID.randomUUID().toString());
				admin_role.setRolename("ROLE_ADMIN");
				admin_role.setInsert_date(DateUtil.now());
				admin_role.setChange_date(null);
				role.save(admin_role);

				Role user_role = new Role();
				user_role.setIdx(UUID.randomUUID().toString());
				user_role.setRolename("ROLE_USER");
				user_role.setInsert_date(DateUtil.now());
				user_role.setChange_date(null);
				role.save(user_role);
			}
			
			{
				mapping_role.deleteAll();
				
				UserMappingRole mapping = new UserMappingRole();
				mapping.setRolename("ROLE_ADMIN");
				mapping.setUsername("admin");
				mapping_role.save(mapping);
			}			
		};
    } 
}
