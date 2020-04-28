package com.competition.jpa.repository.three;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.three.WordThree;

@Repository
public interface ThreeRepository extends JpaRepository<WordThree, Long> {
	WordThree findByIdx(String idx);
	List<WordThree> findByWordIdx(String idx);
}
