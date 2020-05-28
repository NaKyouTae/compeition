package com.competition.jpa.repository.history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.history.LoginHistory;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long>{
	List<LoginHistory> findByUserName(String userName);
	LoginHistory findByIdx(String idx);
}
