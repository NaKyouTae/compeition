package com.mercury.service.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.jpa.model.history.HistoryRequest;
import com.mercury.process.history.HistoryRequestProcess;

@Service
@SuppressWarnings("unchecked")
public class HistoryRequestService {
	
	@Autowired
	private HistoryRequestProcess historyRequestProcess;
	
	public <T extends Object> T seHistoryRequests() throws Exception{
		try {
			return (T) historyRequestProcess.seHistoryRequests();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByIdx(String idx) throws Exception{
		try {
			return (T) historyRequestProcess.seHistoryRequestByIdx(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByUrl(String path) throws Exception{
		try {
			return (T) historyRequestProcess.seHistoryRequestByUrl(path);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByBrowser(String browser) throws Exception{
		try {
			return (T) historyRequestProcess.seHistoryRequestByBrowser(browser);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByIp(String ip) throws Exception{
		try {
			return (T) historyRequestProcess.seHistoryRequestByIp(ip);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByMethod(String method) throws Exception{
		try {
			return (T) historyRequestProcess.seHistoryRequestByMethod(method);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T seHistoryRequestByUserName(String username) throws Exception{
		try {
			return (T) historyRequestProcess.seHistoryRequestByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T inHistoryRequest(HistoryRequest hq) throws Exception{
		try {
			return (T) historyRequestProcess.inHistoryRequest(hq);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T upHistoryRequest(HistoryRequest hq) throws Exception{
		try {
			return (T) historyRequestProcess.upHistoryRequest(hq);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	public <T extends Object> T deHistoryRequest(HistoryRequest hq) throws Exception{
		try {
			return (T) historyRequestProcess.deHistoryRequest(hq);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
