package com.us.cal.gov.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CorrespodingRestControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> AllExpectionHandler(Exception e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
