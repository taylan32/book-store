package com.example.bookstore.service.book;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.bookstore.dto.BookListResponse;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.CategoryType;
import com.example.bookstore.dto.ErrorCode;
import com.example.bookstore.exception.GenericException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookStatus;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.category.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookListService {

	private final BookRepository bookRepository;
	private final CategoryService categoryService;

	public List<BookListResponse> list(int pageNumber, int pageSize, Long userId) {
		return this.bookRepository.findAll(BookSearchSpecification.searchByUserSpecification(userId), 
				PageRequest.of(pageNumber - 1, pageSize))
				.getContent()
				.stream()
				.map(BookListService::convertResponse)
				.collect(Collectors.toList());
	}

	public List<BookListResponse> searchByCategory(CategoryType categoryType, Long userId) {
		final Category category = this.categoryService.loadByName(categoryType.getValue());
		return category.getBooks()
				.stream()
				.filter(book -> book.getUserId().equals(userId))
				.map(BookListService::convertResponse)
				.collect(Collectors.toList());
	}

	public List<BookListResponse> searchByBookStatus(BookStatus bookStatus, Long userId) {
		return this.bookRepository.findAll(BookSearchSpecification.searchByBookStatus(bookStatus, userId))
				.stream()
				.map(book -> 
					BookListResponse.builder()
					.id(book.getId())
					.imageUrl(book.getImage().getImageUrl())
					.build())
				.collect(Collectors.toList());
	}

	public List<BookListResponse> searchByTitle(String title) {
		return this.bookRepository.findAll(BookSearchSpecification.search(title))
				.stream()
				.map(book ->
						BookListResponse.builder()
						.id(book.getId())
						.imageUrl(book.getImage().getImageUrl())
						.build())
				.collect(Collectors.toList());
	}

	public BookResponse findById(Long id) throws Exception {
		final Book book = this.bookRepository.findById(id).orElseThrow(() -> GenericException.builder()
				.httpStatus(HttpStatus.NOT_FOUND)
				.errorCode(ErrorCode.NOT_FOUND)
				.errorMessage("Book not found")
				.build());

		return BookResponse.builder()
				.id(book.getId())
				.bookStatus(book.getBookStatus())
				.publisher(book.getPublisher())
				.authorName(book.getAuthorName())
				.categoryName(book.getCategory().getName())
				.imageUrl(book.getImage().getImageUrl())
				.lastPageNumber(book.getLastPageNumber())
				.totalPage(book.getTotalPage())
				.build();
	}

	private static BookListResponse convertResponse(Book model) {
		return BookListResponse.builder()
				.id(model.getId())
				.authorName(model.getAuthorName())
				.title(model.getTitle())
				.imageUrl(model.getImage().getImageUrl())
				.build();

	}

}
