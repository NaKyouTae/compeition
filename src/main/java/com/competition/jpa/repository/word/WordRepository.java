package com.competition.jpa.repository.word;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.word.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
	
	@Query(value="select * from word where startDate <= date(now()) and endDate >= date(now()) and wordGroup = :group", nativeQuery = true)
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
	
	Word findByIdx(String wordIdx);
}
