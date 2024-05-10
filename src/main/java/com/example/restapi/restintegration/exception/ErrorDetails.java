package com.example.restapi.restintegration.exception;

public class ErrorDetails {
	
	private String status;
	private String message;
	
	
	public ErrorDetails(String status, String message) {
		super();
		this.status = status;
		this.message = message;
		
	}
	
	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ErrorDetails [status=" + status + ", message=" + message + "]";
	}

		
}