package com.mercury.jpa.repository.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.grade.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>{
	Grade findByIdx(String idx);
}
