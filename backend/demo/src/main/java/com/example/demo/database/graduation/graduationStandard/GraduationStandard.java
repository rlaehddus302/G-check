package com.example.demo.database.graduation.graduationStandard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.Enum.Condition;
import com.example.demo.database.graduation.GeneralEducation;
import com.example.demo.database.graduation.Major;
import com.example.demo.database.graduation.course.GradCourse;
import com.example.demo.database.graduation.standardCourse.StandardCourse;
import com.example.demo.database.user.StudentCourse;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class GraduationStandard 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Enumerated(EnumType.STRING)
	private Condition condition;
	
	private String category;
	
	private Integer number;
	
	private String remarks;
	
	@ManyToOne
    @JoinColumn(name = "major_id")
	@JsonIgnore
	private Major major;
	
	@ManyToOne
    @JoinColumn(name = "generalEducation_id")
	@JsonIgnore
	private GeneralEducation generalEducation;

	@OneToMany(mappedBy = "graduationStandard", cascade = CascadeType.ALL)
    private List<StandardCourse> standardCourses;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	private List<GraduationStandard> children;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private GraduationStandard parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "graduationStandard")
    private List<StudentCourse> studentCourse;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public List<StandardCourse> getStandardCourses() {
		return standardCourses;
	}

	public void setStandardCourses(List<StandardCourse> standardCourses) {
		this.standardCourses = standardCourses;
	}

	public List<GraduationStandard> getChildren() {
		return children;
	}

	public void setChildren(List<GraduationStandard> children) {
		this.children = children;
	}

	public GraduationStandard getParent() {
		return parent;
	}

	public void setParent(GraduationStandard parent) {
		this.parent = parent;
	}
}
