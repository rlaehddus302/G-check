package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class GcheckController {
	
    private static final Logger log = LoggerFactory.getLogger(GcheckController.class);

	
	@GetMapping(path = "/test")
	public ResponseEntity<String> test()
	{
		String body = "testSuccess";
		return ResponseEntity.ok(body);
	}
	
}
