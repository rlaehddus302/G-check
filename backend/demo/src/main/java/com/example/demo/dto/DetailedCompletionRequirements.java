package com.example.demo.dto;

import java.util.List;

import com.example.demo.Enum.Condition;

public class DetailedCompletionRequirements 
{
	private String label;
	private int progress;
	private int number;
	private Condition condition;
	private List<RequirementCourseOption> requirementCourseOption;
}
