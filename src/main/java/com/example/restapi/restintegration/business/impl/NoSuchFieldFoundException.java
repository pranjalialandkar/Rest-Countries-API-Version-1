package com.example.restapi.restintegration.business.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchFieldFoundException extends RuntimeException {

	public NoSuchFieldFoundException(String message) {
		super(message);
	}
	

}
