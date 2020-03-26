package com.competition.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.WordThree;

@Repository
public interface ThreeRepository extends JpaRepository<WordThree, Long> {
	List<WordThree> findByWordIdx(String idx);
}
