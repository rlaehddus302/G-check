package com.example.demo.error;


public class DuplicateException extends RuntimeException {

	public DuplicateException(String message) {
		super(message);
	}
}
