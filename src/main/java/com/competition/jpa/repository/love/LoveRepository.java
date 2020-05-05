package com.competition.jpa.repository.love;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.love.Love;

@Repository
public interface LoveRepository extends JpaRepository<Love, Long>{
	Love findByContentIdx(String contentIdx);
}
