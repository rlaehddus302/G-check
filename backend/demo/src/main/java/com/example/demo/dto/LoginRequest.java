package com.example.demo.dto;

public class LoginRequest {

	private String id;
	private String password;
	
	public LoginRequest(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
}
