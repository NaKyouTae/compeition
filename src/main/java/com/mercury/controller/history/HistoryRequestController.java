package com.mercury.controller.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.history.HistoryRequest;
import com.mercury.service.history.HistoryRequestService;

@RestController
@RequestMapping("/server/history/requests")
@SuppressWarnings("unchecked")
public class HistoryRequestController {
	@Autowired
	private HistoryRequestService historyRequestService;
	
	@GetMapping("")
	public <T extends Object> T seHistoryRequests() throws Exception{
		ControllerResponse<List<HistoryRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search HistoryRequests :) ");
			res.setResult(historyRequestService.seHistoryRequests());
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/idx")
	public <T extends Object> T seHistoryRequestByIdx(String idx) throws Exception{
		ControllerResponse<HistoryRequest> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search HistoryRequest By Idx :) ");
			res.setResult(historyRequestService.seHistoryRequestByIdx(idx));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/path")
	public <T extends Object> T seHistoryRequestByUrl(String path) throws Exception{
		ControllerResponse<List<HistoryRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search HistoryRequest By Url :) ");
			res.setResult(historyRequestService.seHistoryRequestByUrl(path));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/browser")
	public <T extends Object> T seHistoryRequestByBrowser(String browser) throws Exception{
		ControllerResponse<List<HistoryRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search HistoryRequest By Browser :) ");
			res.setResult(historyRequestService.seHistoryRequestByBrowser(browser));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/ip")
	public <T extends Object> T seHistoryRequestByIp(String ip) throws Exception{
		ControllerResponse<List<HistoryRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search HistoryRequest By Ip :) ");
			res.setResult(historyRequestService.seHistoryRequestByIp(ip));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/method")
	public <T extends Object> T seHistoryRequestByMethod(String method) throws Exception{
		ControllerResponse<List<HistoryRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search HistoryRequest By Method :) ");
			res.setResult(historyRequestService.seHistoryRequestByMethod(method));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/username")
	public <T extends Object> T seHistoryRequestByUserName(String username) throws Exception{
		ControllerResponse<List<HistoryRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search HistoryRequest By User Name :) ");
			res.setResult(historyRequestService.seHistoryRequestByUserName(username));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PostMapping("")
	public <T extends Object> T inHistoryRequest(HistoryRequest hq) throws Exception{
		ControllerResponse<HistoryRequest> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert HistoryRequest :) ");
			res.setResult(historyRequestService.inHistoryRequest(hq));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PutMapping("")
	public <T extends Object> T upHistoryRequest(HistoryRequest hq) throws Exception{
		ControllerResponse<HistoryRequest> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update HistoryRequest :) ");
			res.setResult(historyRequestService.upHistoryRequest(hq));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@DeleteMapping("")
	public <T extends Object> T deHistoryRequest(HistoryRequest hq) throws Exception{
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete HistoryRequest :) ");
			res.setResult(historyRequestService.deHistoryRequest(hq));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
}
