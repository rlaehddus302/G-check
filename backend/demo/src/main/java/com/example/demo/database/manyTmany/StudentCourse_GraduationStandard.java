package com.example.demo.database.manyTmany;

import com.example.demo.database.graduation.graduationStandard.GraduationStandard;
import com.example.demo.database.user.StudentCourse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentCourse_GraduationStandard 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
    @ManyToOne(optional=false) 
    @JoinColumn(name="graduationStandard_id")
    private GraduationStandard graduationStandard;

    @ManyToOne(optional=false) 
    @JoinColumn(name="studentCourse_id")
    private StudentCourse studentCourse;
    
	public StudentCourse_GraduationStandard() 
	{
	}

	public StudentCourse_GraduationStandard(Long id, GraduationStandard graduationStandard,
			StudentCourse studentCourse) 
	{
		this.id = id;
		this.graduationStandard = graduationStandard;
		this.studentCourse = studentCourse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GraduationStandard getGraduationStandard() {
		return graduationStandard;
	}

	public void setGraduationStandard(GraduationStandard graduationStandard) {
		this.graduationStandard = graduationStandard;
	}

	public StudentCourse getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(StudentCourse studentCourse) {
		this.studentCourse = studentCourse;
	}
}
