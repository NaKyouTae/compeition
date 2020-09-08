package com.mercury.process.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.role.Role;
import com.mercury.jpa.repository.role.RoleRepository;

@Component
@Transactional
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
