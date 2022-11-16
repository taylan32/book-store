package com.example.bookstore.service.book;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.bookstore.dto.BookListResponse;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.CategoryType;
import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookStatus;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.auth.AuthService;
import com.example.bookstore.service.category.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookListService {

	private final BookRepository bookRepository;
	private final CategoryService categoryService;
	private final AuthService authService;

	public List<BookListResponse> list(int pageNumber, int pageSize) {
		return bookRepository.findAll(PageRequest.of(pageNumber - 1, pageSize)).getContent().stream()
				.map(BookListService::convertResponse).collect(Collectors.toList());
	}

	public List<BookListResponse> searchByCategory(CategoryType categoryType) throws Exception {
		final Category category = this.categoryService.loadByName(categoryType.getValue());
		return category.getBooks().stream().map(BookListService::convertResponse).collect(Collectors.toList());
	}
	
	
	public List<BookListResponse> searchByBookStatus(BookStatus bookStatus) {
		return this.bookRepository.findByBookStatus(bookStatus).stream().map(
				item -> BookListResponse.builder()
				.id(item.getId())
				.imageUrl(item.getImage() != null ? item.getImage().getImageUrl() : null)
				.build())
				.collect(Collectors.toList());
	}

	public List<BookListResponse> searchByTitle(String title) {
		return this.bookRepository.findByTitle(title).stream().map(
				item -> BookListResponse.builder()
				.id(item.getId())
				.imageUrl(item.getImage() != null ? item.getImage().getImageUrl() : null)
				.build())
				.collect(Collectors.toList());
	}

	public BookResponse findById(Long id) throws Exception {
		Book book = this.bookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Requested book not found"));
		return BookResponse.builder()
				.id(book.getId())
				.authorName(book.getAuthorName())
				.bookStatus(book.getBookStatus())
				.categoryName(book.getCategory().getName())
				.imageUrl(book.getImage() != null ? book.getImage().getImageUrl() : null)
				.lastPageNumber(book.getLastPageNumber())
				.totalPage(book.getTotalPage())
				.publisher(book.getPublisher())
				.title(book.getTitle()).build();

	}

	private static BookListResponse convertResponse(Book model) {
		return BookListResponse.builder()
				.id(model.getId())
				.authorName(model.getAuthorName())
				.title(model.getTitle())
				.imageUrl(model.getImage() != null ? model.getImage().getImageUrl() : null)
				.build();

	}

}
