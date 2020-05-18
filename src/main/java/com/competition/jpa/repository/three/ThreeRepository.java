package com.competition.jpa.repository.three;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.three.Three;

@Repository
public interface ThreeRepository extends JpaRepository<Three, Long> {
	Three findByIdx(String idx);
	List<Three> findByWordIdx(String idx, Sort sort);
	List<Three> findByUserIdx(String idx, Sort sort);
	
	@Query(value="select sum(t.point) from three t where userIdx = :userIdx", nativeQuery=true)
	Object getTotalPoint(String userIdx);
}
