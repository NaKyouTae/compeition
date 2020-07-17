package com.mercury.jpa.repository.history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.history.HistoryLogin;

@Repository
public interface HistoryLoginRepository extends JpaRepository<HistoryLogin, Long>{
	List<HistoryLogin> findByUserName(String userName);
	HistoryLogin findByIdx(String idx);
}
