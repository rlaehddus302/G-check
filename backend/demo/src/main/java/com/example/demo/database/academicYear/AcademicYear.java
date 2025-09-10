package com.example.demo.database.academicYear;

import com.example.demo.database.department.Department;
import com.example.demo.database.graduation.GraduationRequirements;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class AcademicYear 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int academicYear;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnore
	private Department department;

    @OneToOne(mappedBy = "academicYear", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private GraduationRequirements graduationRequirements;
    
	public AcademicYear() 
	{
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public GraduationRequirements getGraduationRequirements() {
		return graduationRequirements;
	}

	public void setGraduationRequirements(GraduationRequirements graduationRequirements) {
		this.graduationRequirements = graduationRequirements;
	}
}
