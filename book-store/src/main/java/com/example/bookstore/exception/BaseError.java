package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseError {

	private HttpStatus httpStatus;
	private String type;
	private String title;
	private String details;
	
	
}
