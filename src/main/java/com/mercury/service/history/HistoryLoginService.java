package com.mercury.service.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.history.HistoryLogin;
import com.mercury.process.history.HistoryLoginProcess;

@Service
@SuppressWarnings("unchecked")
public class HistoryLoginService {
	
	@Autowired
	private HistoryLoginProcess loginHistoryProcess;
	
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
	
	public <T extends Object> T inLoginHistory(HistoryLogin history) throws Exception {
		try {
			return (T) loginHistoryProcess.upLoginHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	public <T extends Object> T upLoginHistory(HistoryLogin history) throws Exception {
		try {
			return (T) loginHistoryProcess.upLoginHistory(history);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
