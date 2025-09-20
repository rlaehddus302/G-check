package com.example.demo.database.graduation.standardCourse;

import com.example.demo.database.graduation.course.GradCourse;
import com.example.demo.database.graduation.graduationStandard.GraduationStandard;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StandardCourse 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
    @ManyToOne(optional=false) 
    @JoinColumn(name="graduationStandard_id")
    @JsonIgnore
    private GraduationStandard graduationStandard;

    @ManyToOne(optional=false) 
    @JoinColumn(name="course_id")
    private GradCourse gradCourse;

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

	public GradCourse getCourse() {
		return gradCourse;
	}

	public void setCourse(GradCourse gradCourse) {
		this.gradCourse = gradCourse;
	}
    
    
}
