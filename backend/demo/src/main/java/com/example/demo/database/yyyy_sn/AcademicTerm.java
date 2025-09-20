package com.example.demo.database.yyyy_sn;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AcademicTerm 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int academicYear;
	private String semester;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "academicTerm")
	private List<Course> courses;
	
	public AcademicTerm() 
	{
		
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYear() {
		return academicYear;
	}

	public void setYear(int year) {
		this.academicYear = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
}
