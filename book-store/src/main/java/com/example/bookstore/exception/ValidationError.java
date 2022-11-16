package com.example.bookstore.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ValidationError extends BaseError {

	private Map<String, String> errors;

	public ValidationError(HttpStatus httpStatus, String type, String title, String details,
			Map<String, String> errors) {
		super(httpStatus, type, title, details);
		this.errors = errors;
	}

}
