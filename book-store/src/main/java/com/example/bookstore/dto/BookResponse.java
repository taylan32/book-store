package com.example.bookstore.dto;

import com.example.bookstore.model.BookStatus;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookResponse {
	
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
