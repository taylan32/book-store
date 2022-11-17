package com.example.bookstore.service.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.dto.SaveBookResponse;
import com.example.bookstore.dto.request.SaveBookRequest;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.category.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateBookService {

	private final BookRepository bookRepository;
	private final CategoryService categoryService;
	
	@Transactional
	public SaveBookResponse saveBook(SaveBookRequest request) throws Exception {
		// TODO: fix imageUrl
		Category category = categoryService.loadCategory(request.getCategoryId());
		final Book book = Book.builder()
			.category(category)
			.authorName(request.getAuthorName())
			.bookStatus(request.getBookStatus())
			.title(request.getTitle())
			.lastPageNumber(request.getLastPageNumber())
			.totalPage(request.getTotalPage())
			.publisher(request.getPublisher())
			.build();
		Book addedBook = bookRepository.save(book);
		
		return SaveBookResponse.builder()
				.id(addedBook.getId())
				.authorName(addedBook.getAuthorName())
				.categoryName(addedBook.getCategory().getName())
				.bookStatus(addedBook.getBookStatus())
				.title(addedBook.getTitle())
				.publisher(addedBook.getPublisher())
				.totalPage(addedBook.getTotalPage())
				.lastPageNumber(addedBook.getLastPageNumber())
				.build();		
	}
	
}
