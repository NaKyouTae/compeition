package com.mercury.jpa.repository.honor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.honor.Honor;

@Repository
public interface HonorRepository extends JpaRepository<Honor, Long>{
	Honor findByIdx(String idx);
	List<Honor> findByUserName(String username);
	List<Honor> findByContentIdx(String contentIdx);
	List<Honor> findByWord(String word);
}
