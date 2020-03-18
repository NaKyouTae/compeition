package com.competition.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.model.WeekWord;

@Repository
public interface WeekWordRepository extends JpaRepository<WeekWord, Long> {
	
	@Query(value = "select * from weekword where DATE(start_date) <= :nowData and DATE(end_date) >= :nowData", nativeQuery = true)
	WeekWordDto findByWord(@Param("nowData") String nowData);
}
