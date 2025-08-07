package com.example.demo.error;


public class DuplicateIdException extends RuntimeException {

	public DuplicateIdException(String message) {
		super(message);
	}
}
