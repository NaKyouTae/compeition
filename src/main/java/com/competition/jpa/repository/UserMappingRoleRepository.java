package com.competition.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.UserMappingRole;

@Repository
public interface UserMappingRoleRepository extends JpaRepository<UserMappingRole, Long> {
	List<UserMappingRole> findByUsername(String username);
	
	List<UserMappingRole> findByRolename(String rolename);
}
