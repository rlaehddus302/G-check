package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.department.Department;
import com.example.demo.database.department.DepartmentRepository;
import com.example.demo.database.user.Student;
import com.example.demo.database.user.StudentRepository;

import jakarta.validation.Valid;

@RestController
public class AuthController 
{
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	
	@PostMapping("/register/checkDuplicate")
	public ResponseEntity<String> checkDuplicate(@RequestParam String id)
	{
		System.out.println(id);
		Optional<Student> student = studentRepository.findByUserID(id);
		if(student.isEmpty())
		{
			return ResponseEntity.ok("사용 가능한 아이디입니다.");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 있는 아이디입니다.");
	}
	
	@GetMapping("/register/departments")
	public ResponseEntity<List<Department>> findDepartmentsByName(@RequestParam String name)
	{
		List<Department> departments = departmentRepository.findByDepartmentContaining(name);
		return null;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Valid Student student)
	{
		if(student == null)
		{
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		student.setRole("ROLE_USER");
		studentRepository.save(student);
		URI location = URI.create("/users/" + student.getId());
		return ResponseEntity.created(location).build();
	}
}
