package com.example.bookstore.dto;

import java.io.File;

import com.example.bookstore.model.Book;
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
	    private File image;
	    private String categoryName;
	    private Integer totalPage;
	    private Long userId;
	    private String imageUrl;

	    public static BookListResponse from(Book book) {
	        return  BookListResponse.builder()
	                .id(book.getId())
	                .title(book.getTitle())
	                .authorName(book.getAuthorName())
	                .bookStatus(book.getBookStatus())
	                .publisher(book.getPublisher())
	                .lastPageNumber(book.getLastPageNumber())
	                .categoryName(book.getCategory().getName())
	                .totalPage(book.getTotalPage())
	                .userId(book.getUserId())
	                .imageUrl(book.getImage() != null ? book.getImage().getImageUrl() : null)
	                .build();
	    }

}
