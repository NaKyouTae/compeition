package com.competition.jpa.repository.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.grade.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>{
	Grade findByIdx(String idx);
}
