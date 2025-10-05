package com.example.demo.dto;

import java.util.List;

public class RequirementProgress 
{
	private String label;          
	private int completedCredits;  
	private int requiredCredits;   
	private int progress;
	private List<DetailedCompletionRequirements> detailedCompletionRequirements;
	
	public RequirementProgress(String label, int completedCredits, int requiredCredits, int progress,
			List<DetailedCompletionRequirements> detailedCompletionRequirements) 
	{
		this.label = label;
		this.completedCredits = completedCredits;
		this.requiredCredits = requiredCredits;
		this.progress = progress;
		this.detailedCompletionRequirements = detailedCompletionRequirements;
	}
	public String getLabel() 
	{
		return label;
	}
	public void setLabel(String label) 
	{
		this.label = label;
	}
	public int getCompletedCredits() 
	{
		return completedCredits;
	}
	public void setCompletedCredits(int completedCredits) 
	{
		this.completedCredits = completedCredits;
	}
	public int getRequiredCredits() 
	{
		return requiredCredits;
	}
	public void setRequiredCredits(int requiredCredits) 
	{
		this.requiredCredits = requiredCredits;
	}
	public int getProgress() 
	{
		return progress;
	}
	public void setProgress(int progress) 
	{
		this.progress = progress;
	}
	public List<DetailedCompletionRequirements> getDetailedCompletionRequirements()
	{
		return detailedCompletionRequirements;
	}
	public void setDetailedCompletionRequirements(List<DetailedCompletionRequirements> detailedCompletionRequirements) 
	{
		this.detailedCompletionRequirements = detailedCompletionRequirements;
	}
}
