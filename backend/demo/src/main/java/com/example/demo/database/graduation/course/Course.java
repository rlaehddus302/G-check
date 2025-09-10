package com.example.demo.database.graduation.course;

import java.util.List;

import com.example.demo.database.graduation.graduationStandard.GraduationStandard;
import com.example.demo.database.graduation.standardCourse.StandardCourse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Course 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private int credit;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StandardCourse> standardCourses;
	
	public Course() 
	{
		
	}	
}
