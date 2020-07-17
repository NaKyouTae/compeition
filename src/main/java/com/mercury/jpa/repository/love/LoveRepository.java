package com.mercury.jpa.repository.love;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.love.Love;

@Repository
public interface LoveRepository extends JpaRepository<Love, Long>{
	Love findByContentIdx(String contentIdx);
	
	@Query(value="SELECT idx, userIdx, contentIdx, insertDate FROM mercury_tb_love WHERE userIdx=:userIdx AND contentIdx=:contentIdx", nativeQuery=true)
	Love findByUserLove(@Param("userIdx")String userIdx, @Param("contentIdx")String contentIdx);
}
