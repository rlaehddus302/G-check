package com.example.demo.database.graduation.graduationStandard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.Enum.Condition;
import com.example.demo.database.graduation.GeneralEducation;
import com.example.demo.database.graduation.Major;
import com.example.demo.database.graduation.course.Course;
import com.example.demo.database.graduation.standardCourse.StandardCourse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class GraduationStandard 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Enumerated(EnumType.STRING)
	private Condition condition;
	
	private String category;
	
	private Integer number;
	
	private String remarks;
	
	@ManyToOne
    @JoinColumn(name = "major_id")
	private Major major;
	
	@ManyToOne
    @JoinColumn(name = "generalEducation_id")
	private GeneralEducation generalEducation;

	@OneToMany(mappedBy = "graduationStandard", cascade = CascadeType.ALL)
    private List<StandardCourse> standardCourses;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	private List<GraduationStandard> children;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private GraduationStandard parent;
    
}
