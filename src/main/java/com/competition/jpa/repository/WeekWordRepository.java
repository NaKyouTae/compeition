package com.competition.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.WeekWord;

@Repository
public interface WeekWordRepository extends JpaRepository<WeekWord, Long> {
	
	@Query(value="select * from weekword where DATE(start_date) <= :nowData and DATE(end_date) >= :nowData", nativeQuery = true)
	test findByWord(@Param("nowData") String nowData);
	
	public static interface test{
		String getIdx();
		String getWord_group();
		String getWord();
		String getInsert_date();
		String getStart_date();
		String getEnd_date();
	}
}
