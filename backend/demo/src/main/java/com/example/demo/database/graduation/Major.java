package com.example.demo.database.graduation;

import java.util.List;

import com.example.demo.database.graduation.graduationStandard.GraduationStandard;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Major 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int totalCredit;
	
	@OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true)
	List<GraduationStandard> graduationStandards;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GraduationRequirements_id")
	@JsonIgnore
	GraduationRequirements graduationRequirements;

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

	public List<GraduationStandard> getGraduationStandards() {
		return graduationStandards;
	}

	public void setGraduationStandards(List<GraduationStandard> graduationStandards) {
		this.graduationStandards = graduationStandards;
	}

	public GraduationRequirements getGraduationRequirements() {
		return graduationRequirements;
	}

	public void setGraduationRequirements(GraduationRequirements graduationRequirements) {
		this.graduationRequirements = graduationRequirements;
	}
	
	
}
