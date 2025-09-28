package com.example.demo.dto;

import com.example.demo.Enum.Language;

public class CourseInfoDTO 
{
	private Long id;
	
	private String name;
	
	private int credit;
	
	private double score;
	
	private Language language;

	private String category;
	
	public CourseInfoDTO(Long id, String name, int credit, double score, Language language, String category) {
		super();
		this.id = id;
		this.name = name;
		this.credit = credit;
		this.score = score;
		this.language = language;
		this.category = category;
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
