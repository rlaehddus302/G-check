package com.example.demo.database.graduation.graduationRequirements;

import com.example.demo.database.graduation.GraduationRequirements;

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
	private GraduationRequirements graduationRequirements;
	
	private String content;
	
	public Other() 
	{
		
	}
	
}
