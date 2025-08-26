package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GcheckController {
	
	@GetMapping(path = "/test")
	public ResponseEntity<String> test()
	{
		String body = "testSuccess";
		return ResponseEntity.ok(body);
	}
}
