package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo 
{
	private Long id;
	private String userID;
	private String name;
	private int admissionYear;
	private String major;
	
	public UserInfo(Long id, String userID, String name, int admissionYear, String major) {
		super();
		this.id = id;
		this.userID = userID;
		this.name = name;
		this.admissionYear = admissionYear;
		this.major = major;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAdmissionYear() {
		return admissionYear;
	}

	public void setAdmissionYear(int admissionYear) {
		this.admissionYear = admissionYear;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
}
