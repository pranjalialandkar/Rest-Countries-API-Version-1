package com.example.restapi.restintegration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.restapi.restintegration.business.impl.CountryNotFoundException;


@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

//	/*
//	 * @ExceptionHandler(Exception.class) public final ResponseEntity<ErrorDetails>
//	 * handleAllExceptions(Exception ex, WebRequest request) throws Exception {
//	 * ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
//	 * ex.getMessage(), request.getDescription(false));
//	 * 
//	 * return new ResponseEntity<ErrorDetails>(errorDetails,
//	 * HttpStatus.INTERNAL_SERVER_ERROR);
//	 * 
//	 * }
//	 */
	@ExceptionHandler(CountryNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleCountryNotFoundException(CountryNotFoundException ex, WebRequest request) throws Exception {
		
		
		ErrorDetails errorDetails = new ErrorDetails(ex.getStatusCode().toString(), ex.getMessage());
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
			
}


