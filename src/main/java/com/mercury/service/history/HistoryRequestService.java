package com.mercury.service.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.jpa.model.history.HistoryRequest;
import com.mercury.process.history.HistoryRequestProcess;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class HistoryRequestService {

	@Autowired
	private HistoryRequestProcess historyRequestProcess;

	public <T extends Object> T seHistoryRequests() throws Exception {
		return (T) historyRequestProcess.seHistoryRequests();
	}
	public <T extends Object> T seHistoryRequestByIdx(String idx)
			throws Exception {
		return (T) historyRequestProcess.seHistoryRequestByIdx(idx);
	}
	public <T extends Object> T seHistoryRequestByUrl(String path)
			throws Exception {
		return (T) historyRequestProcess.seHistoryRequestByUrl(path);
	}
	public <T extends Object> T seHistoryRequestByBrowser(String browser)
			throws Exception {
		return (T) historyRequestProcess.seHistoryRequestByBrowser(browser);
	}
	public <T extends Object> T seHistoryRequestByIp(String ip)
			throws Exception {
		return (T) historyRequestProcess.seHistoryRequestByIp(ip);
	}
	public <T extends Object> T seHistoryRequestByMethod(String method)
			throws Exception {
		return (T) historyRequestProcess.seHistoryRequestByMethod(method);
	}
	public <T extends Object> T seHistoryRequestByUserName(String username)
			throws Exception {
		return (T) historyRequestProcess.seHistoryRequestByUserName(username);
	}
	public <T extends Object> T inHistoryRequest(HistoryRequest hq)
			throws Exception {
		return (T) historyRequestProcess.inHistoryRequest(hq);
	}
	public <T extends Object> T upHistoryRequest(HistoryRequest hq)
			throws Exception {
		return (T) historyRequestProcess.upHistoryRequest(hq);
	}
	public <T extends Object> T deHistoryRequest(HistoryRequest hq)
			throws Exception {
		return (T) historyRequestProcess.deHistoryRequest(hq);
	}
}
