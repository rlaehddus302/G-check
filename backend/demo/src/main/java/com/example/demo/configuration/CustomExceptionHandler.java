package com.example.demo.configuration;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.AuthErrorDTO;
import com.example.demo.error.DuplicateIdException;
import com.example.demo.error.IdBlankException;
import com.example.demo.error.InvalidYearException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{
	@ExceptionHandler(exception = InvalidYearException.class)
	public ResponseEntity<AuthErrorDTO> invalidYear(Exception ex, WebRequest request)
	{
		AuthErrorDTO errorDTO = new AuthErrorDTO(LocalDate.now(),ex.getMessage(), "admissionYear");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
	}
	
	@ExceptionHandler(exception = DuplicateIdException.class)
	public ResponseEntity<AuthErrorDTO> duplicateId(Exception ex, WebRequest request)
	{
		AuthErrorDTO errorDTO = new AuthErrorDTO(LocalDate.now(),ex.getMessage(), "userID");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
	}
	
	@ExceptionHandler(exception = IdBlankException.class)
	public ResponseEntity<AuthErrorDTO> IdBlank(Exception ex, WebRequest request)
	{
		AuthErrorDTO errorDTO = new AuthErrorDTO(LocalDate.now(),ex.getMessage(), "userID");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
	}

	@Override
	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) 
	{
		FieldError errors = ex.getBindingResult().getFieldError();
		AuthErrorDTO errorDTO = new AuthErrorDTO(LocalDate.now(), errors.getDefaultMessage(), errors.getField());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
	}
}
