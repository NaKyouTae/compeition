package com.mercury.jpa.repository.word;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.word.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
	@Query(value="select idx, wordgroup, word, insertdate, startdate, enddate, description from mercury_tb_word where startDate <= date(now()) and endDate >= date(now()) and wordGroup = :group", nativeQuery = true)
	Word findByWord(@Param("group") String group);
	Word findByIdx(String wordIdx);
}
