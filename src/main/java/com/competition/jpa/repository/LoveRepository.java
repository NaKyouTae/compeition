package com.competition.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.Love;

@Repository
public interface LoveRepository extends JpaRepository<Love, Long>{
	Love findByContentIdx(String idx);
}
