package com.example.bookstore.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseError> handleException(Exception exception) {
		return new ResponseEntity<BaseError>(new BaseError(HttpStatus.INTERNAL_SERVER_ERROR,
				"example.com/probs/internal", "Internal Server Error", exception.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<NotFoundError> handleNotFoundException(NotFoundException exception) {
		return new ResponseEntity<NotFoundError>(new NotFoundError(HttpStatus.NOT_FOUND, "example.com/probs/notfound",
				"No item found", exception.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BusinessError> handleBusinessException(BusinessException exception) {
		return new ResponseEntity<BusinessError>(new BusinessError(HttpStatus.BAD_REQUEST, "example.com/probs/business",
				"Business Exception", exception.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> handleValidationException(MethodArgumentNotValidException exception) {
		Map<String, String> validationExceptions = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(item -> {
			validationExceptions.put(((FieldError)item).getField(), item.getDefaultMessage());
		});
		return new ResponseEntity<ValidationError>(new ValidationError(HttpStatus.BAD_REQUEST,
				"example.com/probs/validation", "Validation Exception(s)", "Validation Error.", validationExceptions),
				HttpStatus.BAD_REQUEST);

	}

}
