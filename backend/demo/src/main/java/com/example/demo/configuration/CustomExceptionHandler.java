package com.example.demo.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.error.InvalidYearException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{
	@ExceptionHandler(exception = InvalidYearException.class)
	public ResponseEntity<Object> invalidYear(Exception ex, WebRequest request)
	{
		
		return null;
	}
}
