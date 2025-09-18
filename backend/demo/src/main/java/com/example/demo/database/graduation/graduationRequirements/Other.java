package com.example.demo.database.graduation.graduationRequirements;

import com.example.demo.database.graduation.GraduationRequirements;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Other 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "graduationRequirements_id")
	@JsonIgnore
	private GraduationRequirements graduationRequirements;
	
	private String content;
	
	public Other() 
	{
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public GraduationRequirements getGraduationRequirements() {
		return graduationRequirements;
	}

	public void setGraduationRequirements(GraduationRequirements graduationRequirements) {
		this.graduationRequirements = graduationRequirements;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
