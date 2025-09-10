package com.example.demo.database.graduation;

import java.util.List;

import com.example.demo.database.graduation.graduationStandard.GraduationStandard;

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
public class GeneralEducation 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int totalCredit;
	
	@OneToMany(mappedBy = "generalEducation", cascade = CascadeType.ALL, orphanRemoval = true)
	List<GraduationStandard> GraduationStandards;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GraduationRequirements_id")
	GraduationRequirements graduationRequirements;
}
