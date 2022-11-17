package com.example.bookstore.dto.request;

import java.io.File;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.bookstore.model.BookStatus;
import lombok.Data;

@Data
public final class SaveBookRequest {

	@NotBlank(message = "Title is not allowed to be empty")
	private String title;

	@NotBlank(message = "Auhtor name is not allowed to be empty")
	private String authorName;

	@NotNull
	private BookStatus bookStatus;

	@NotBlank(message = "Publisher is not allowed to be empty")
	private String publisher;
	
	@NotNull
	private Integer lastPageNumber;
	@NotNull
	private Integer totalPage;
	

	//@NotBlank(message = "Title is not allowed to be empty")
	private File image;
	
	@NotNull
	private Long categoryId;
	
	
}
