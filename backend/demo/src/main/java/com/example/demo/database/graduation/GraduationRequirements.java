package com.example.demo.database.graduation;

import java.util.List;

import com.example.demo.Enum.Condition;
import com.example.demo.database.academicYear.AcademicYear;
import com.example.demo.database.graduation.graduationRequirements.Other;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class GraduationRequirements 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int totalCredit;
	
	private int toeic;
	
	private double averageScore;
	
	private int requireEnglishCourse;
	
	private int requireMinimumEnglishMajorCourse;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academicYear_id", nullable = false)
	private AcademicYear academicYear;
	
	@Enumerated(EnumType.STRING)
	private Condition condition;
	
	private int number;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "graduationRequirements", orphanRemoval = true)
	private List<Other> others;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "graduationRequirements")
	private Major major;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "graduationRequirements")
	private GeneralEducation generalEducation;
	
	public GraduationRequirements() 
	{
		
	}
		
	
}
