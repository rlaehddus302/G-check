package com.example.demo.database.graduation.course;

import java.util.List;

import com.example.demo.database.graduation.graduationStandard.GraduationStandard;
import com.example.demo.database.graduation.standardCourse.StandardCourse;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class GradCourse 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private int credit;
	
	@OneToMany(mappedBy = "gradCourse", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<StandardCourse> standardCourses;
	
	public GradCourse() 
	{
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public List<StandardCourse> getStandardCourses() {
		return standardCourses;
	}

	public void setStandardCourses(List<StandardCourse> standardCourses) {
		this.standardCourses = standardCourses;
	}
}
