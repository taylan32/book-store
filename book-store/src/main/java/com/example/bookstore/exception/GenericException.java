package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;

import com.example.bookstore.dto.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericException extends RuntimeException{

	private HttpStatus httpStatus;
	private String errorMessage;
	private ErrorCode errorCode;
	
}
