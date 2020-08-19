package com.mercury.controller.honor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.honor.Honor;
import com.mercury.service.honor.HonorService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/honor")
public class HonorController {
	@Autowired
	private HonorService honorService;
	
	@GetMapping("")
	public <T extends Object> T seHonors() throws Exception{
		ControllerResponse<List<Honor>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Honor List :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(honorService.seHonors());
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/idx")
	public <T extends Object> T seHonorByIdx(String idx) throws Exception{
		ControllerResponse<Honor> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Honor By Idx :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(honorService.seHonorByIdx(idx));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/usernmae")
	public <T extends Object> T seHonorByUserName(String username) throws Exception{
		ControllerResponse<List<Honor>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Honor By User Name :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(honorService.seHonorByUserName(username));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/contentidx")
	public <T extends Object> T seHonorByContentIdx(String contentIdx) throws Exception{
		ControllerResponse<List<Honor>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Honor By Content Idx :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(honorService.seHonorByContentIdx(contentIdx));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("/word")
	public <T extends Object> T seHonorByWord(String word) throws Exception{
		ControllerResponse<List<Honor>> res = new ControllerResponse<>();
		try {
			res.setMessage("Success Search Honor By Word :) ");
			res.setResultCode(HttpStatus.OK);
			res.setResult(honorService.seHonorByWord(word));
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(e.getMessage());
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResult(null);
		}
		
		return (T) res;
	}
}
