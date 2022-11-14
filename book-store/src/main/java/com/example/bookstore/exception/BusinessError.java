package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;

public class BusinessError extends BaseError {

	public BusinessError(HttpStatus httpStatus, String type, String title, String details) {
		super(httpStatus, type, title, details);
	}

	
	
}
