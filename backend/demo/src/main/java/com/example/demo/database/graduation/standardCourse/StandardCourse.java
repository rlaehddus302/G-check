package com.example.demo.database.graduation.standardCourse;

import com.example.demo.database.graduation.course.Course;
import com.example.demo.database.graduation.graduationStandard.GraduationStandard;

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
    private GraduationStandard graduationStandard;

    @ManyToOne(optional=false) 
    @JoinColumn(name="course_id")
    private Course course;
}
