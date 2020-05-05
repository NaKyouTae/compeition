package com.competition.controller.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.jpa.model.notice.Notice;
import com.competition.service.notice.NoticeService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/notices")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
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
	public <T extends Object> T seNotice(String idx) throws Exception{
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
