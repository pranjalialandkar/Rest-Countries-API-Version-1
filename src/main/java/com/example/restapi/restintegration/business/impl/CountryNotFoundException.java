package com.example.restapi.restintegration.business.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException {
	
	private String statusCode;

	public CountryNotFoundException( String statusCode,String statusText) {
		super(statusText);
		this.statusCode = statusCode;
	}

	public String getStatusCode() { 
		return statusCode; 
		
	}
	

}