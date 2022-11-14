package com.example.bookstore.dto;

import java.io.File;

import com.example.bookstore.model.BookStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookListResponse {
	
	private Long id;
	private String title;
	private String authorName;
	private BookStatus bookStatus;
	private String publisher;
	private Integer lastPageNumber;
	private Integer totalPage;
	private String imageUrl;
	private String categoryName;

}