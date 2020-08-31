package com.mercury.controller.cash;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.cash.CashRequest;
import com.mercury.service.cash.CashRequestService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/cashrequest")
public class CashRequestController {
	
	Logger log = LogManager.getLogger(CashRequestController.class);
	
	@Autowired
	private CashRequestService cashRequestService;
	
	@PutMapping("/approval/{idx}")
	public <T extends Object> T approvalCash(@PathParam(value = "idx") String idx) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Approval Cash Request :) ");
			res.setResult(cashRequestService.approvalCash(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/users")
	public <T extends Object> T seCashByUserName(String username) throws Exception{
		ControllerResponse<List<CashRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Cash Request List By User Name :) ");
			res.setResult(cashRequestService.seCashByUserName(username));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping
	public <T extends Object> T seCashs() throws Exception{
		ControllerResponse<List<CashRequest>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Cash Request List :) ");
			res.setResult(cashRequestService.seCashs());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/{idx}")
	public <T extends Object> T seCash(@PathParam(value = "idx") String idx) throws Exception{
		ControllerResponse<CashRequest> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Cash Request :) ");
			res.setResult(cashRequestService.seCash(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@PostMapping
	public <T extends Object> T inCash(@RequestBody CashRequest cash) throws Exception{
		ControllerResponse<CashRequest> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Cash Request :) ");
			res.setResult(cashRequestService.inCash(cash));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
}
