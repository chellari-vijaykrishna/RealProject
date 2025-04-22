package com.us.cal.gov.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataCollectionRestControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> AllExceptionHandler(Exception e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
	}
}
