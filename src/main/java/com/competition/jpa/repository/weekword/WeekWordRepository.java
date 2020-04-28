package com.competition.jpa.repository.weekword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.weekword.WeekWord;

@Repository
public interface WeekWordRepository extends JpaRepository<WeekWord, Long> {
	
	@Query(value="select * from weekword where startDate <= date(now()) and endDate >= date(now()) and wordGroup = :group", nativeQuery = true)
	WordInter findByWord(@Param("group") String group);
	
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
