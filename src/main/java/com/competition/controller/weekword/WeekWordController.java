package com.competition.controller.weekword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.dto.weekword.WeekWordDto;
import com.competition.jpa.model.WeekWord;
import com.competition.service.weekword.WeekWordService;
import com.competition.vo.weekword.WeekWordVO;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/weekword")
public class WeekWordController {

	@Autowired
	private WeekWordService weekWordService;
	
	@GetMapping("/lists")
	public <T extends Object> T getLists() throws Exception {
		ControllerResponse<List<WeekWord>> res = new ControllerResponse<List<WeekWord>>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Week Word List :) "); 
			res.setResult(weekWordService.getLists());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/words")
	public <T extends Object> T geWeekWords(@Param(value = "group") String group) throws Exception {
		ControllerResponse<WeekWord> res = new ControllerResponse<WeekWord>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Week Word :) "); 
			res.setResult(weekWordService.getWeekWords(group));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@PostMapping("/words")
	public <T extends Object> T inWord(@RequestBody WeekWordVO vo) throws Exception {
		ControllerResponse<WeekWord> res = new ControllerResponse<WeekWord>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert Week Word :) "); 
			res.setResult(weekWordService.inWord(vo));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	@PutMapping("/words/{idx}")
	public <T extends Object> T upWord(@RequestBody WeekWordVO vo) throws Exception {
		ControllerResponse<WeekWord> res = new ControllerResponse<WeekWord>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update Week Word :) "); 
			res.setResult(weekWordService.upWord(vo));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
	@DeleteMapping("/words/{idx}")
	public <T extends Object> T deWord(@RequestBody WeekWordVO vo) throws Exception {
		ControllerResponse<WeekWordDto> res = new ControllerResponse<WeekWordDto>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete Week Word :) "); 
			res.setResult(weekWordService.deWord(vo));
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return (T) res;
	}
}
