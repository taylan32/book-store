package com.example.bookstore.dto.converter;

import com.example.bookstore.dto.BookListResponse;
import com.example.bookstore.dto.request.SaveBookRequest;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Category;

public class BookDtoConverter {
	
	public static Book convertToBookDto(SaveBookRequest request, Category category, Long userId) {
		return Book.builder()
				.category(category)
				.bookStatus(request.getBookStatus())
				.title(request.getTitle())
				.publisher(request.getPublisher())
				.lastPageNumber(request.getLastPageNumber())
				.authorName(request.getAuthorName())
				.totalPage(request.getTotalPage())
				.userId(userId)
				.build();
	}

	
	public static BookListResponse toItem(Book book) {
		return BookListResponse.builder()
				.categoryName(book.getCategory().getName())
				.bookStatus(book.getBookStatus())
				.title(book.getTitle())
				.publisher(book.getPublisher())
				.lastPageNumber(book.getLastPageNumber())
				.authorName(book.getAuthorName())
				.totalPage(book.getTotalPage())
				.userId(book.getUserId())
				.build();
	}
	
}
