package com.mercury.controller.notice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercury.common.ControllerResponse;
import com.mercury.jpa.model.notice.Notice;
import com.mercury.jpa.model.user.User;
import com.mercury.jpa.model.user.UserNotice;
import com.mercury.service.notice.NoticeService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/notices")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@PostMapping("/never")
	public <T extends Object> T neverOpen(@RequestBody Map<Object, Object> map) throws Exception {
		ControllerResponse<UserNotice> res = new ControllerResponse<>();
		ObjectMapper mapper = new ObjectMapper();
		
		Notice notice = mapper.convertValue(map.get("notice"), Notice.class);
		User user = mapper.convertValue(map.get("user"), User.class);
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success, Never Announced :) "); 
			res.setResult(noticeService.neverOpen(notice ,user));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/pop")
	public <T extends Object> T seNoticePop(String type, String username) throws Exception{
		ControllerResponse<List<Notice>> res = new ControllerResponse<>();

		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Notice PopUp List :) "); 
			res.setResult(noticeService.seNoticePop(type, username));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("")
	public <T extends Object> T seNotices() throws Exception{
		ControllerResponse<List<Notice>> res = new ControllerResponse<>();

		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Notice List :) "); 
			res.setResult(noticeService.seNotices());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/{idx}")
	public <T extends Object> T seNotice(@PathVariable String idx) throws Exception{
		ControllerResponse<Notice> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Notice :) "); 
			res.setResult(noticeService.seNotice(idx));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PostMapping("")
	public <T extends Object> T inNotice(@RequestBody Notice notice) throws Exception{
		ControllerResponse<Notice> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Notice :) "); 
			res.setResult(noticeService.inNotice(notice));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PutMapping("/{idx}")
	public <T extends Object> T upNotice(@RequestBody Notice notice) throws Exception{
		ControllerResponse<Notice> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update Notice :) "); 
			res.setResult(noticeService.upNotice(notice));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	@DeleteMapping("/{idx}")
	public <T extends Object> T deNotice(@RequestBody Notice notice) throws Exception{
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Notice :) "); 
			res.setResult(noticeService.deNotice(notice));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
}
