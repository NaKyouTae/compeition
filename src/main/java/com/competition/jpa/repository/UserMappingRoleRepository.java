package com.competition.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.UserMappingRole;

@Repository
public interface UserMappingRoleRepository extends JpaRepository<UserMappingRole, Long> {
	UserMappingRole findByUsername(String username);
	
	UserMappingRole findByRolename(String rolename);
}
