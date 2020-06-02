package com.competition.controller.mail;

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
import com.competition.jpa.model.mail.NewsLetter;
import com.competition.service.mail.NewsLetterService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/newsletters")
public class NewsLetterController {

	@Autowired
	private NewsLetterService newsLetterService;
	
	@GetMapping("/users/names")
	public <T extends Object> T seNewsLetterByUserName(String username) throws Exception {
		ControllerResponse<NewsLetter> res = new ControllerResponse<>();
		
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search NewsLetter By User Name :) ");
			res.setResult(newsLetterService.seNewsLetterByUserName(username));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/users/idxs")
	public <T extends Object> T seNewsLetterByUserIdx(String userIdx) throws Exception {
		ControllerResponse<NewsLetter> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search NewsLetter By User Idx :) ");
			res.setResult(newsLetterService.seNewsLetterByUserIdx(userIdx));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@GetMapping("")
	public <T extends Object> T seNewsLetters() throws Exception {
		ControllerResponse<List<NewsLetter>> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search NewsLetter List :) ");
			res.setResult(newsLetterService.seNewsLetters());
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
	
	@GetMapping("/idxs")
	public <T extends Object> T seNewsLetter(String idx) throws Exception {
		ControllerResponse<NewsLetter> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Search NewsLetter :) ");
			res.setResult(newsLetterService.seNewsLetter(idx));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@PostMapping("")
	public <T extends Object> T inNewsLetter(@RequestBody NewsLetter news) throws Exception {
		ControllerResponse<NewsLetter> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Insert NewsLetter :) ");
			res.setResult(newsLetterService.inNewsLetter(news));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@PutMapping("/{idx}")
	public <T extends Object> T upNewsLetter(@RequestBody NewsLetter news) throws Exception {
		ControllerResponse<NewsLetter> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Update NewsLetter :) ");
			res.setResult(newsLetterService.upNewsLetter(news));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		return (T) res;
	}
	
	@DeleteMapping("")
	public <T extends Object> T deNewsLetter(@RequestBody String username) throws Exception {
		ControllerResponse<Boolean> res = new ControllerResponse<>();
		try {
			res.setResultCode(HttpStatus.OK);
			res.setMessage("Success Delete NewsLetter :) ");
			res.setResult(newsLetterService.deNewsLetter(username));
		} catch (Exception e) {
			e.printStackTrace();
			res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
			res.setResult(null);
		}
		
		return (T) res;
	}
}
