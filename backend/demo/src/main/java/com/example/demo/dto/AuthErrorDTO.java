package com.example.demo.dto;

import java.time.LocalDate;

public class AuthErrorDTO 
{
	private Long id;
	private LocalDate date;
	private String message;
	private String field;
	
	public AuthErrorDTO(LocalDate date, String message, String field) {
		this.date = date;
		this.message = message;
		this.field = field;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
