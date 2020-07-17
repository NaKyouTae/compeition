package com.mercury.jpa.repository.two;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.two.Two;

@Repository
public interface TwoRepository extends JpaRepository<Two, Long> {
	Two findByIdx(String idx);
	List<Two> findByWordIdx(String idx, Sort sort);
	List<Two> findByUserIdx(String idx, Sort sort);
	@Query(value="select sum(w.point) as point from mercury_tb_two w where userIdx = :userIdx", nativeQuery = true)
	Integer getTotalPoint(@Param("userIdx") String userIdx);
}
