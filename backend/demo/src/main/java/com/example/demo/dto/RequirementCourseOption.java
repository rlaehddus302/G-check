package com.example.demo.dto;

public class RequirementCourseOption 
{
    String courseName;
    int credit;            
    boolean status;
    
	public RequirementCourseOption(String courseName, int credit, boolean status) {
		super();
		this.courseName = courseName;
		this.credit = credit;
		this.status = status;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
