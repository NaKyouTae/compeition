package com.competition.service.role;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.role.Role;
import com.competition.process.role.RoleProcess;
import com.competition.util.DateUtil;

@Service
@SuppressWarnings("unchecked")
public class RoleService {

	@Autowired
	private RoleProcess roleProcess;
	
	public <T extends Object> T getRoles() throws Exception {
		try {
			return (T) roleProcess.getRoles();
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T getRole(String idx) throws Exception {
		try {			
			return (T) roleProcess.getRole(idx);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T inRole(Role role) throws Exception {
		try {
			role.setIdx(UUID.randomUUID().toString().replace("-", ""));
			role.setInsertDate(DateUtil.now());
			
			return (T) roleProcess.inRole(role);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T upRole(Role role) throws Exception {
		try {
			role.setChangeDate(DateUtil.now());
			return (T) roleProcess.upRole(role);
		} catch (Exception e) {
			return (T) e;
		}
	}
	public <T extends Object> T deRole(Role role) throws Exception {
		try {			
			roleProcess.deRole(role);
			return (T) Boolean.TRUE; 
		} catch (Exception e) {
			return (T) e;
		}
	}
	
	
}
