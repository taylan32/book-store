package com.example.bookstore.dto;

import java.io.File;

import javax.validation.constraints.NotBlank;

import com.example.bookstore.model.BookStatus;

import lombok.Data;

@Data
public final class SaveBookRequest {

	@NotBlank(message = "Title is not allowed to be empty")
	private String title;

	@NotBlank(message = "Auhtor name is not allowed to be empty")
	private String authorName;

	//@NotBlank(message = "Book status is not allowed to be empty")
	private BookStatus bookStatus;

	@NotBlank(message = "Publisher is not allowed to be empty")
	private String publisher;
	
	
	private Integer lastPageNumber;
	private Integer totalPage;
	

	//@NotBlank(message = "Title is not allowed to be empty")
	private File image;
	
	private Long categoryId;
	
	
}
