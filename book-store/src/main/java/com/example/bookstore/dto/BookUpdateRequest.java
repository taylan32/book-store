package com.example.bookstore.dto;

import java.io.File;

import com.example.bookstore.model.BookStatus;

import lombok.Data;

@Data
public class BookUpdateRequest {

	private Long id;
	private String title;
	private String authorName;
	private BookStatus bookStatus;
	private String publisher;
	private Integer lastPageNumber;
	private Integer totalPage;
	private Long categoryId;
	private File image;
	
	
}
