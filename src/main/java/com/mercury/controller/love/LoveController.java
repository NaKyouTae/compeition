package com.mercury.controller.love;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.love.Love;
import com.mercury.service.love.LoveService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/loves")
public class LoveController {

	@Autowired
	private LoveService loveService;
	
	@GetMapping("/totals")
	public <T extends Object> T seTotalLove(String userIdx) throws Exception {
		ControllerResponse<Integer> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Serach My Total Love Count :) "); 
			res.setResult(loveService.seTotalLove(userIdx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/{idx}")
	public <T extends Object> T seLove(String idx) throws Exception {
		ControllerResponse<Love> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Love History :) ");
			res.setResult(loveService.seLove(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/check")
	public <T extends Object> T checkLove(String contentIdx, String userIdx) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		
		if(userIdx == null) {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("User Name is null...");
			res.setResult(Boolean.FALSE);
			return (T) res;
		}
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search Love History :) ");
			res.setResult(loveService.checkLove(contentIdx, userIdx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PostMapping("")
	public <T extends Object> T inLove(@RequestBody Love love) throws Exception {
		ControllerResponse<Love> res = new ControllerResponse<Love>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Love :) "); 
			res.setResult(loveService.inLove(love));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@DeleteMapping("")
	public <T extends Object> T deLove(@RequestBody Map<String, String> idx) throws Exception{
		ControllerResponse<Boolean> res = new ControllerResponse<Boolean>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Love :) "); 
			res.setResult(loveService.deLove(idx.get("userIdx"), idx.get("contentIdx")));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
}
