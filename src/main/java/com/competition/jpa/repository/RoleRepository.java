package com.competition.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleName(String rolename);
	Role findByIdx(String idx);
}
