package com.competition.controller.newsletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.competition.jpa.model.newsletter.NewsLetter;
import com.competition.service.newsletter.NewsLetterService;

@RestController
@SuppressWarnings("unchecked")
@RequestMapping("/service/newsletters")
public class NewsLetterController {

	@Autowired
	private NewsLetterService newsLetterService;
	
	@GetMapping("/{username}")
	public <T extends Object> T seNewsLetterByUserName(String username) throws Exception {
		try {
			return (T) newsLetterService.seNewsLetterByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	@GetMapping("/{userIdx}")
	public <T extends Object> T seNewsLetterByUserIdx(String userIdx) throws Exception {
		try {
			return (T) newsLetterService.seNewsLetterByUserIdx(userIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	@GetMapping("")
	public <T extends Object> T seNewsLetters() throws Exception {
		try {
			return (T) newsLetterService.seNewsLetters();
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	@GetMapping("/{idx}")
	public <T extends Object> T seNewsLetter(String idx) throws Exception {
		try {
			return (T) newsLetterService.seNewsLetter(idx);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	@PostMapping("")
	public <T extends Object> T inNewsLetter(NewsLetter news) throws Exception {
		try {
			return (T) newsLetterService.inNewsLetter(news);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	@PutMapping("/{idx}")
	public <T extends Object> T upNewsLetter(NewsLetter news) throws Exception {
		try {
			return (T) newsLetterService.upNewsLetter(news);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
	
	@DeleteMapping("")
	public <T extends Object> T deNewsLetter(NewsLetter news) throws Exception {
		try {
			return (T) newsLetterService.deNewsLetter(news);
		} catch (Exception e) {
			e.printStackTrace();
			return (T) e;
		}
	}
}
