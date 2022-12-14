package com.example.bookstore.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.dto.BookListResponse;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.dto.CategoryType;
import com.example.bookstore.dto.SaveBookResponse;
import com.example.bookstore.dto.request.BookUpdateRequest;
import com.example.bookstore.dto.request.SaveBookRequest;
import com.example.bookstore.model.BookStatus;
import com.example.bookstore.service.book.BookListService;
import com.example.bookstore.service.book.BookUpdateService;
import com.example.bookstore.service.book.CreateBookService;
import com.example.bookstore.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {
	
	private final BookListService bookListService;
	private final CreateBookService createBookService;
	private final UserService userService;
	private final BookUpdateService bookUpdateService;
	
	
	@PostMapping("/save")
	public ResponseEntity<SaveBookResponse> saveBook(@RequestBody @Valid SaveBookRequest request) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(this.createBookService.saveBook(request));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<BookListResponse>> list(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
		final Long userId = this.userService.findUserInContext().getId();
		return ResponseEntity.ok(this.bookListService.list(pageNumber, pageSize, userId));
	}
	
	@GetMapping("/list/{categoryType}")
	public ResponseEntity<List<BookListResponse>> searchByCategory(@PathVariable CategoryType categoryType) throws Exception {
		Long userId = this.userService.findUserInContext().getId();
		return ResponseEntity.ok(this.bookListService.searchByCategory(categoryType, userId));
	}
	
	
	@GetMapping("/list/{bookStatus}")
	public ResponseEntity<List<BookListResponse>> searchByBookStatus(@PathVariable BookStatus bookStatus) throws Exception {
		Long userId = this.userService.findUserInContext().getId();
		return ResponseEntity.ok(this.bookListService.searchByBookStatus(bookStatus, userId));
	}

	@GetMapping("/list/{title}")
	public ResponseEntity<List<BookListResponse>> searchByTitle(@PathVariable String title) {
		return ResponseEntity.ok(this.bookListService.searchByTitle(title));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookResponse> findById(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok(this.bookListService.findById(id));
	}
	
	@PutMapping("/update")
	public ResponseEntity<BookResponse> update(@RequestBody BookUpdateRequest request) throws Exception {
		return ResponseEntity.ok(this.bookUpdateService.update(request));
	}
	
	
	
}
