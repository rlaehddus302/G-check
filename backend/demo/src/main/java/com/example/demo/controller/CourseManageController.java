package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.yyyy_sn.Course;
import com.example.demo.database.yyyy_sn.CourseRepository;

@RestController
public class CourseManageController 
{
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping(path = "/courses/search")
	public ResponseEntity<List<Course>> findCoursesByYearAndSemesterAndName(@RequestParam int year, @RequestParam String semester, @RequestParam String name)
	{
		List<Course> courses = courseRepository.findByAcademicTerm(year, semester, name);
		if(courses.size() == 0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courses);
		}
		return ResponseEntity.ok(courses);
	}
	
}
