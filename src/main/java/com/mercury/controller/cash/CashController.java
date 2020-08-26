package com.mercury.controller.cash;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.cash.Cash;
import com.mercury.service.cash.CashService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/cashs")
public class CashController {
	
	Logger log = LogManager.getLogger(CashController.class);
	
	@Autowired
	private CashService cashService;
	
	@PutMapping("/approval/{idx}")
	public <T extends Object> T approvalCash(@PathParam(value = "idx") String idx) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Approval Cash :) ");
			res.setResult(cashService.approvalCash(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/users")
	public <T extends Object> T seCashByUserName(String username) throws Exception{
		ControllerResponse<List<Cash>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Cash List By User Name :) ");
			res.setResult(cashService.seCashByUserName(username));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/approvals")
	public <T extends Object> T seCashByApproval(Boolean approval) throws Exception {
		ControllerResponse<List<Cash>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Cash List By Approval :) ");
			res.setResult(cashService.seCashByApprovals(approval));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("")
	public <T extends Object> T seCashs() throws Exception{
		ControllerResponse<List<Cash>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Cash List :) ");
			res.setResult(cashService.seCashs());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/{idx}")
	public <T extends Object> T seCash(String idx) throws Exception{
		ControllerResponse<Cash> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Cash :) ");
			res.setResult(cashService.seCash(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@PostMapping
	public <T extends Object> T inCash(Cash cash) throws Exception{
		ControllerResponse<Cash> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Cash :) ");
			res.setResult(cashService.inCash(cash));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@PutMapping
	public <T extends Object> T upCash(Cash cash) throws Exception{
		ControllerResponse<Cash> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update Cash :) ");
			res.setResult(cashService.upCash(cash));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@DeleteMapping
	public <T extends Object> T deCash(Cash cash) throws Exception{
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Cash :) ");
			res.setResult(cashService.deCash(cash));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
}
