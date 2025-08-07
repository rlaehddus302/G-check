package com.example.demo.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.department.Department;
import com.example.demo.database.department.DepartmentRepository;
import com.example.demo.database.user.Student;
import com.example.demo.database.user.StudentRepository;
import com.example.demo.error.DuplicateIdException;
import com.example.demo.error.InvalidYearException;

import jakarta.validation.Valid;

@RestController
public class AuthController 
{
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/register/checkDuplicate")
	public ResponseEntity<Map<String, String>> checkDuplicate(@RequestParam String id)
	{
		Optional<Student> student = studentRepository.findByUserID(id);
		if(!student.isEmpty())
		{
			throw new DuplicateIdException("이미 있는 아이디입니다.");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "사용 가능한 아이디입니다.");
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/register/departments")
	public ResponseEntity<List<Department>> findDepartmentsByName(@RequestParam String name)
	{
		List<Department> departments = departmentRepository.findByDepartmentContaining(name);
		if(departments.size() == 0 )
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departments);

		}
		return ResponseEntity.status(HttpStatus.OK).body(departments);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Valid Student student)
	{
		int admissionYear = student.getAdmissionYear();
		if( admissionYear > LocalDate.now().getYear() || admissionYear < 2020)
		{
			throw new InvalidYearException("지원하지 않는 년도입니다.");
		}
		Optional<Student> exist = studentRepository.findByUserID(student.getUserID());
		if(!exist.isEmpty())
		{
			throw new DuplicateIdException("이미 있는 아이디입니다.");
		}
		student.setRole("ROLE_USER");
		String password = student.getPassword();
		password = passwordEncoder.encode(password);
		System.out.println(password);
		student.setPassword(password);
		studentRepository.save(student);
		URI location = URI.create("/users/" + student.getId());
		return ResponseEntity.created(location).build();
	}
}
