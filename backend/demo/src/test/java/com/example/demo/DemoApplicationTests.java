package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.database.academicYear.AcademicYear;
import com.example.demo.database.department.Department;
import com.example.demo.database.department.DepartmentRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class DemoApplicationTests {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Test
	void contextLoads() 
	{
		
	}

}
