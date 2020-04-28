package com.competition.process.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.role.Role;
import com.competition.jpa.repository.role.RoleRepository;

@Component
@SuppressWarnings("unchecked")
public class RoleProcess {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public <T extends Object> T getRoles() {
		return (T) roleRepository.findAll();
	}
	
	public <T extends Object> T getRole(String idx) {
		return (T) roleRepository.findByIdx(idx);
	}
	
	public <T extends Object> T inRole(Role role) {
		return (T) roleRepository.save(role);
	}
	public <T extends Object> T upRole(Role role) {
		return (T) roleRepository.save(role);
	}
	public void deRole(Role role) {
		roleRepository.delete(role);
	}

}
