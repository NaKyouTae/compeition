package com.mercury.service.role;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.role.Role;
import com.mercury.process.role.RoleProcess;
import com.mercury.util.DateUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class RoleService {

	@Autowired
	private RoleProcess roleProcess;

	public <T extends Object> T getRoles() throws Exception {
		return (T) roleProcess.getRoles();
	}
	public <T extends Object> T getRole(String idx) throws Exception {
		return (T) roleProcess.getRole(idx);
	}
	public <T extends Object> T inRole(Role role) throws Exception {
		role.setIdx(UUID.randomUUID().toString().replace("-", ""));
		role.setInsertDate(DateUtil.now());

		return (T) roleProcess.inRole(role);
	}
	public <T extends Object> T upRole(Role role) throws Exception {
		role.setChangeDate(DateUtil.now());
		return (T) roleProcess.upRole(role);
	}
	public <T extends Object> T deRole(Role role) throws Exception {
		roleProcess.deRole(role);
		return (T) Boolean.TRUE;
	}

}
