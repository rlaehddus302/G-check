package com.example.demo.database.graduation;

import java.util.List;

import com.example.demo.Enum.Condition;
import com.example.demo.database.academicYear.AcademicYear;
import com.example.demo.database.graduation.graduationRequirements.Other;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public int getToeic() {
		return toeic;
	}

	public void setToeic(int toeic) {
		this.toeic = toeic;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public int getRequireEnglishCourse() {
		return requireEnglishCourse;
	}

	public void setRequireEnglishCourse(int requireEnglishCourse) {
		this.requireEnglishCourse = requireEnglishCourse;
	}

	public int getRequireMinimumEnglishMajorCourse() {
		return requireMinimumEnglishMajorCourse;
	}

	public void setRequireMinimumEnglishMajorCourse(int requireMinimumEnglishMajorCourse) {
		this.requireMinimumEnglishMajorCourse = requireMinimumEnglishMajorCourse;
	}

	public AcademicYear getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Other> getOthers() {
		return others;
	}

	public void setOthers(List<Other> others) {
		this.others = others;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public GeneralEducation getGeneralEducation() {
		return generalEducation;
	}

	public void setGeneralEducation(GeneralEducation generalEducation) {
		this.generalEducation = generalEducation;
	}
		
	
}
