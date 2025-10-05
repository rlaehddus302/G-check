package com.example.demo.dto;

import java.util.List;

import com.example.demo.Enum.Condition;

public class DetailedCompletionRequirements 
{
	private String label;
	private int progress;
	private Integer number;
	private Condition condition;
	private List<RequirementCourseOption> requirementCourseOption;
	
	public DetailedCompletionRequirements(String label, int progress, Integer number, Condition condition,
			List<RequirementCourseOption> requirementCourseOption) 
	{
		this.label = label;
		this.progress = progress;
		this.number = number;
		this.condition = condition;
		this.requirementCourseOption = requirementCourseOption;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public List<RequirementCourseOption> getRequirementCourseOption() {
		return requirementCourseOption;
	}

	public void setRequirementCourseOption(List<RequirementCourseOption> requirementCourseOption) {
		this.requirementCourseOption = requirementCourseOption;
	}	
}
