package com.example.demo.database.user;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Student 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	@NotBlank(message = "빈칸 및 공백이 없어야 합니다.")
	private String userID;
	@NotBlank(message = "빈칸 및 공백이 없어야 합니다.")
	private String name;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message = "빈칸 및 공백이 없어야 합니다.")
	private String password;
	private String role;
	private int admissionYear;
	@NotBlank(message = "빈칸 및 공백이 없어야 합니다.")
	private String major;
	
	public Student() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
