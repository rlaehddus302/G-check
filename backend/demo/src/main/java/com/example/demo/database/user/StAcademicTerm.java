package com.example.demo.database.user;

import java.util.List;

import com.example.demo.Enum.Language;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class StAcademicTerm 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int academicYear;
	
	private String semester;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stAcademicTerm")
	List<StudentCourse> studentCourses;
	
	@ManyToOne()
	@JoinColumn(name = "student_id")
	Student student;
	
	public StAcademicTerm() 
	{
	}

	public StAcademicTerm(Long id, int academicYear, String semester, List<StudentCourse> studentCourses,
			Student student) {
		super();
		this.id = id;
		this.academicYear = academicYear;
		this.semester = semester;
		this.studentCourses = studentCourses;
		this.student = student;
	}

	public Long getId() {
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

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public List<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
