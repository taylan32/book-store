package com.example.bookstore.service.book;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.ErrorCode;
import com.example.bookstore.dto.request.BookUpdateRequest;
import com.example.bookstore.exception.GenericException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.category.CategoryService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookUpdateService {
	
	private final BookRepository bookRepository;
	private final CategoryService categoryService;
	
	@Transactional(rollbackOn = Exception.class)
	public BookResponse update(BookUpdateRequest request) throws Exception {
		Book book = this.bookRepository.findById(request.getId()).orElseThrow(() -> GenericException.builder()
				.errorCode(ErrorCode.NOT_FOUND)
				.errorMessage("Book not found")
				.httpStatus(HttpStatus.NOT_FOUND)
				.build());
		
		// TODO: handle file update
		updateAttributes(request, book);
		
		if(request.getCategoryId() == null) {
			updateCategory(book, request.getCategoryId());
		}
		
		Book updatedBook = this.bookRepository.save(book);
		return BookResponse.builder()
				.id(updatedBook.getId())
				.authorName(updatedBook.getAuthorName())
				.bookStatus(updatedBook.getBookStatus())
				.categoryName(updatedBook.getCategory().getName())
				.imageUrl(updatedBook.getImage() != null ? updatedBook.getImage().getImageUrl() : null)
				.lastPageNumber(updatedBook.getLastPageNumber())
				.totalPage(updatedBook.getTotalPage())
				.publisher(updatedBook.getPublisher())
				.title(updatedBook.getTitle())
				.build();
		
	}
	
	private void updateCategory(Book book, Long categoryId) throws Exception {
		Category category = this.categoryService.loadCategory(categoryId);
		book.setCategory(category);
	}
	private static void updateAttributes(BookUpdateRequest request, Book book) {
		
		book.setTitle(getOrDefault(request.getTitle(), book.getTitle()));
		book.setAuthorName(getOrDefault(request.getAuthorName(), book.getAuthorName()));
		book.setBookStatus(getOrDefault(request.getBookStatus(), book.getBookStatus()));
		book.setLastPageNumber(getOrDefault(request.getLastPageNumber(), book.getLastPageNumber()));
		book.setTotalPage(getOrDefault(request.getTotalPage(), book.getTotalPage()));
		book.setPublisher(getOrDefault(request.getPublisher(), book.getPublisher()));
		
	}
	
	private static <T> T getOrDefault(T data, T defaultValue) {
		return data == null ? defaultValue : data;
	}
	
}
