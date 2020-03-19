package com.competition.controller.weekword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.common.ControllerResponse;
import com.competition.dto.weekword.WeekWordDto;
import com.competition.service.weekword.WeekWordService;
import com.competition.vo.weekword.WeekWordVO;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/weekword")
public class WeekWordController {

	@Autowired
	private WeekWordService weekWordService;
	
	@GetMapping("/lists")
	public ControllerResponse<WeekWordDto> geWeekWords() throws Exception {
		ControllerResponse<WeekWordDto> res = new ControllerResponse<WeekWordDto>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Get Week Word :) "); 
			res.setResult(weekWordService.getWeekWords());
		} catch (Exception e) {
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage()); 
			res.setResult(null);
		}
		
		return res;
	}
	
	@PostMapping("/words")
	public <T extends Object> T inWord(WeekWordVO vo) throws Exception {
		return (T) weekWordService.inWord(vo);
	}
	@PutMapping("/words/{idx}")
	public <T extends Object> T upWord(WeekWordVO vo) throws Exception {
		return (T) weekWordService.upWord(vo);
	}
	@DeleteMapping("/words/{idx}")
	public <T extends Object> T deWord(WeekWordVO vo) throws Exception {
		return (T) weekWordService.deWord(vo);
	}
}
