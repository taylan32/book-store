package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;

public class AuthError extends BaseError {

	public AuthError(HttpStatus httpStatus, String type, String title, String details) {
		super(httpStatus, type, title, details);
		// TODO Auto-generated constructor stub
	}

}
