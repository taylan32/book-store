package com.example.bookstore.dto;

import lombok.Data;

@Data
public class BookSearchRequest {

	private int pageSize;
	private int pageNumber;
}
