package com.mercury.jpa.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.user.UserGrade;

@Repository
public interface UserGradeRepository extends JpaRepository<UserGrade, Long> {
	UserGrade findByUserName(String userName);
	
	UserGrade findByGradeName(String gradeName);
}
