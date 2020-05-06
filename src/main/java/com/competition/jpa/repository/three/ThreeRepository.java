package com.competition.jpa.repository.three;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.three.Three;

@Repository
public interface ThreeRepository extends JpaRepository<Three, Long> {
	Three findByIdx(String idx);
	List<Three> findByWordIdx(String idx, Sort sort);
}
