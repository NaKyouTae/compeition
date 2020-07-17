package com.mercury.jpa.repository.history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercury.jpa.model.history.HistoryRequest;

@Repository
public interface HistoryRequestRepository extends JpaRepository<HistoryRequest, Long>{
	HistoryRequest findByIdx(String idx);
	List<HistoryRequest> findByUsername(String username); 
	List<HistoryRequest> findByRequestUtl(String path);
	List<HistoryRequest> findByBrowser(String browser);
	List<HistoryRequest> findByMethod(String method);
	List<HistoryRequest> findByIp(String ip);
	
}
