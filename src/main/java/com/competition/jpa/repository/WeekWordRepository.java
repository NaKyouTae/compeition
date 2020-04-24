package com.competition.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.WeekWord;

@Repository
public interface WeekWordRepository extends JpaRepository<WeekWord, Long> {
	
	@Query(value="select * from weekword where DATE(start_date) <= :nowData and DATE(end_date) >= :nowData and word_group = :group", nativeQuery = true)
	WordInter findByWord(@Param("group") String group, @Param("nowData") String nowData);
	
	public static interface WordInter{
		String getIdx();
		String getWordGroup();
		String getWord();
		String getInsertDate();
		String getStartDate();
		String getEndDate();
		String getDescription();
	}
}
