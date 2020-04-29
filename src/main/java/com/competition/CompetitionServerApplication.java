package com.competition;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.competition.config.interceptor.JwtInterceptor;
import com.competition.jpa.model.role.Role;
import com.competition.jpa.model.user.User;
import com.competition.jpa.model.user.UserRole;
import com.competition.jpa.repository.menu.MenuRepository;
import com.competition.jpa.repository.role.RoleRepository;
import com.competition.jpa.repository.user.UserRepository;
import com.competition.jpa.repository.user.UserRoleRepository;
import com.competition.util.DateUtil;

@SpringBootApplication
public class CompetitionServerApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CompetitionServerApplication.class, args);
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
        .allowedOrigins("http://localhost:4300", "http://localhost:8080")
        .allowedMethods("GET","POST", "PUT", "DELETE")
        .exposedHeaders("Access-JWT", "Refresh-JWT")
        .allowCredentials(true).maxAge(3600);
    }
	
	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/user/**");
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public CommandLineRunner runner(UserRepository user, UserRoleRepository mapping_role, RoleRepository role, MenuRepository menu) {
		return (args) -> {
			{
				user.deleteAll();
				
				User u = new User();
				u.setIdx(UUID.randomUUID().toString());
				u.setUserName("admin");
				u.setPw(passwordEncoder.encode("skrbxo12!@"));
				u.setInsertDate(DateUtil.now());
				u.setChangeDate(null);
				user.save(u);
				
				User u1 = new User();
				u1.setIdx(UUID.randomUUID().toString());
				u1.setUserName("test");
				u1.setPw(passwordEncoder.encode("test"));
				u1.setInsertDate(DateUtil.now());
				u1.setChangeDate(null);
				user.save(u1);
			}
			
			{
				role.deleteAll();
				
				Role admin_role = new Role();
				admin_role.setIdx(UUID.randomUUID().toString().replace("-", ""));
				admin_role.setRoleName("ROLE_ADMIN");
				admin_role.setInsertDate(DateUtil.now());
				admin_role.setChangeDate(null);
				role.save(admin_role);

				Role user_role = new Role();
				user_role.setIdx(UUID.randomUUID().toString().replace("-", ""));
				user_role.setRoleName("ROLE_USER");
				user_role.setInsertDate(DateUtil.now());
				user_role.setChangeDate(null);
				role.save(user_role);
			}
			
			{
				mapping_role.deleteAll();
				
				UserRole mapping = new UserRole();
				mapping.setRoleName("ROLE_ADMIN");
				mapping.setUserName("admin");
				mapping_role.save(mapping);
			}			
		};
    } 
}
