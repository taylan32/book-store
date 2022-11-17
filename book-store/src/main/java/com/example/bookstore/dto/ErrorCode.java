package com.example.bookstore.dto;

public enum ErrorCode {

	NOT_FOUND(404), BAD_REQUEST(400), FORBIDDEN(403), UNAUTHORIZED(401), INTERNAL_SERVER_ERROR(500), FOUND(302);

	private int value;
	ErrorCode(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
			
}
