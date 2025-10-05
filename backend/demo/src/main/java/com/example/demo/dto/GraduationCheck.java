package com.example.demo.dto;

import java.util.List;

public class GraduationCheck 
{
	private int totalCredit;
	private int progress;
	private List<RequirementProgress> requirementProgress;
	private int completedCredits;
	
	public GraduationCheck(int totalCredit, int progress, List<RequirementProgress> requirementProgress,
			int completedCredits) {
		super();
		this.totalCredit = totalCredit;
		this.progress = progress;
		this.requirementProgress = requirementProgress;
		this.completedCredits = completedCredits;
	}
	public int getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public List<RequirementProgress> getRequirementProgress() {
		return requirementProgress;
	}
	public void setRequirementProgress(List<RequirementProgress> requirementProgress) {
		this.requirementProgress = requirementProgress;
	}
	public int getCompletedCredits() {
		return completedCredits;
	}
	public void setCompletedCredits(int completedCredits) {
		this.completedCredits = completedCredits;
	}  
}
