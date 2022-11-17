package com.example.bookstore.service.book;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookStatus;

@Service
public class BookSearchSpecification {
	
	public static Specification<Book> search(String value) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + value + "%");
	}
	
	public static Specification<Book> searchByBookStatus(BookStatus bookStatus, Long userId) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.and(
					criteriaBuilder.equal(root.get("bookStatus"), bookStatus),
					criteriaBuilder.equal(root.get("userId"), userId)
				);
	}
	
	public static Specification<Book> searchByUserSpecification(Long userId) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
	}
	

}
