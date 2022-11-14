package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;

public class NotFoundError extends BaseError{

	public NotFoundError(HttpStatus httpStatus, String type, String title, String details) {
		super(httpStatus, type, title, details);
		
	}

}
