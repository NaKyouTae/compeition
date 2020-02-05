package com.competition.three.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server/three")
public class ThreeController {

	@GetMapping("/list")
	public Object getList(@ModelAttribute("word") String word) {
		return new Object();
	}
}
