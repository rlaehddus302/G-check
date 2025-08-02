package com.example.demo.error;


public class InvalidYearException extends RuntimeException 
{

	public InvalidYearException(String message) {
		super(message);
	}
	
}
