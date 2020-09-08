package com.mercury.service.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.history.HistoryLogin;
import com.mercury.process.history.HistoryLoginProcess;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class HistoryLoginService {

	@Autowired
	private HistoryLoginProcess loginHistoryProcess;

	public <T extends Object> T seLoginHistoryByUserName(String userName)
			throws Exception {
		return (T) loginHistoryProcess.seLoginHistoryByUserName(userName);
	}

	public <T extends Object> T seLoginHistorys() throws Exception {
		return (T) loginHistoryProcess.seLoginHistorys();
	}

	public <T extends Object> T seLoginHistory(String idx) throws Exception {
		return (T) loginHistoryProcess.seLoginHistory(idx);
	}

	public <T extends Object> T inLoginHistory(HistoryLogin history)
			throws Exception {
		return (T) loginHistoryProcess.upLoginHistory(history);
	}

	public <T extends Object> T upLoginHistory(HistoryLogin history)
			throws Exception {
		return (T) loginHistoryProcess.upLoginHistory(history);
	}
}
