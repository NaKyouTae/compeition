package com.mercury.jpa.repository.notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.notice.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
	Notice findByIdx(String idx);
	
	@Query(value="select idx, title, content, insertDate, type from notice where type = :type", nativeQuery=true)
	List<Notice> findByType(@Param("type") String type);
}
