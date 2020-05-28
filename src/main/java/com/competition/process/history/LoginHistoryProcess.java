package com.competition.process.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.history.LoginHistory;
import com.competition.jpa.repository.history.LoginHistoryRepository;

@Component
@SuppressWarnings("unchecked")
public class LoginHistoryProcess {
	@Autowired
	private LoginHistoryRepository loginHistoryRepository;
	
	public <T extends Object> T seLoginHistoryByUserName(String userName) throws Exception {
		try {
			return (T) loginHistoryRepository.findByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seLoginHistorys() throws Exception{
		try {
			return (T) loginHistoryRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seLoginHistory(String idx) throws Exception {
		try {
			return (T) loginHistoryRepository.findByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inLoginHistory(LoginHistory history) throws Exception {
		try {
			return (T) loginHistoryRepository.save(history);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upLoginHistory(LoginHistory history) throws Exception {
		try {
			return (T) loginHistoryRepository.save(history);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
}
