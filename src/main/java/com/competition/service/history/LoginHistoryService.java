package com.competition.service.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.competition.jpa.model.history.LoginHistory;
import com.competition.process.history.LoginHistoryProcess;

@Service
@SuppressWarnings("unchecked")
public class LoginHistoryService {
	
	@Autowired
	private LoginHistoryProcess loginHistoryProcess;
	
	public <T extends Object> T seLoginHistoryByUserName(String userName) throws Exception {
		try {
			return (T) loginHistoryProcess.seLoginHistoryByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seLoginHistorys() throws Exception{
		try {
			return (T) loginHistoryProcess.seLoginHistorys();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T seLoginHistory(String idx) throws Exception {
		try {
			return (T) loginHistoryProcess.seLoginHistory(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T inLoginHistory(LoginHistory history) throws Exception {
		try {
			return (T) loginHistoryProcess.upLoginHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upLoginHistory(LoginHistory history) throws Exception {
		try {
			return (T) loginHistoryProcess.upLoginHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
