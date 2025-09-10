package com.example.demo.database.department;

import java.util.List;

import com.example.demo.database.academicYear.AcademicYear;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@Column(unique = true)
	private String department;
	
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AcademicYear> academicYears;
	
	public Department()
	{	
	}
	
	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public String getDepartment() 
	{
		return department;
	}

	public void setDepartment(String department) 
	{
		this.department = department;
	}

	public List<AcademicYear> getAcademicYears() {
		return academicYears;
	}

	public void setAcademicYears(List<AcademicYear> academicYears) {
		this.academicYears = academicYears;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
