package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.graduation.GraduationRequirements;
import com.example.demo.database.graduation.GraduationRequirementsRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class GcheckController {
	
    private static final Logger log = LoggerFactory.getLogger(GcheckController.class);
    @Autowired
    private GraduationRequirementsRepository graduationRequirementsRepository;
	
	@GetMapping(path = "/test")
	public ResponseEntity<String> test()
	{
		String body = "testSuccess";
		return ResponseEntity.ok(body);
	}
	
	/*@Transactional(readOnly = true)
	@GetMapping(path = "/graduation-requirements")
	public ResponseEntity<GraduationRequirements> getGradRequire(@RequestParam String department, @RequestParam int year) throws NotFoundException
	{
		System.out.println(department);
		System.out.println(year);
		GraduationRequirements gradRequir = graduationRequirementsRepository.findOneByDeptAndYear(department, year)
        .orElseThrow(() -> new NotFoundException());
		return ResponseEntity.ok(gradRequir);
	}*/
	@Transactional(readOnly = true)
	@GetMapping(path = "/graduation-requirements")
	public ResponseEntity<GraduationRequirements> getGradRequire(@RequestParam String department, @RequestParam int year) throws NotFoundException
	{
		GraduationRequirements gradRequir = graduationRequirementsRepository.findOneByDeptAndYear(department, year)
        .orElseThrow(() -> new NotFoundException());
		return ResponseEntity.ok(gradRequir);
	}
}
