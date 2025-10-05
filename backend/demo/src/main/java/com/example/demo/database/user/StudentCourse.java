package com.example.demo.database.user;

import java.util.List;

import com.example.demo.Enum.Language;
import com.example.demo.database.graduation.graduationStandard.GraduationStandard;
import com.example.demo.database.manyTmany.StudentCourse_GraduationStandard;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class StudentCourse 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private int credit;
	
	private double score;

	@Enumerated(EnumType.STRING)
	private Language language;
	
	private String category;
	
	@ManyToOne()
	@JoinColumn(name = "stAcademicTerm_id")
	private StAcademicTerm stAcademicTerm;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "studentCourse")
	private List<StudentCourse_GraduationStandard> studentCourse_graduationStandards;
	
	public StudentCourse() 
	{
	}
	
	public StudentCourse(Long id, String name, int credit, double score, Language language, String category,
			StAcademicTerm stAcademicTerm, List<StudentCourse_GraduationStandard> studentCourse_graduationStandards) 
	{
		this.id = id;
		this.name = name;
		this.credit = credit;
		this.score = score;
		this.language = language;
		this.category = category;
		this.stAcademicTerm = stAcademicTerm;
		this.studentCourse_graduationStandards = studentCourse_graduationStandards;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public StAcademicTerm getStAcademicTerm() {
		return stAcademicTerm;
	}

	public void setStAcademicTerm(StAcademicTerm stAcademicTerm) {
		this.stAcademicTerm = stAcademicTerm;
	}
	
	public List<StudentCourse_GraduationStandard> getStudentCourse_graduationStandards() {
		return studentCourse_graduationStandards;
	}


	public void setStudentCourse_graduationStandards(
			List<StudentCourse_GraduationStandard> studentCourse_graduationStandards) {
		this.studentCourse_graduationStandards = studentCourse_graduationStandards;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
