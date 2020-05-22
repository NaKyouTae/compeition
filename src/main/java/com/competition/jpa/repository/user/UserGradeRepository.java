package com.competition.jpa.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.user.UserGrade;
import com.competition.jpa.model.user.UserRole;

@Repository
public interface UserGradeRepository extends JpaRepository<UserGrade, Long> {
	List<UserGrade> findByUserName(String userName);
	
	List<UserGrade> findByGradeName(String gradeName);
}
