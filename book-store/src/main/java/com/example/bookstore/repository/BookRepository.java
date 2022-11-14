package com.example.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookStatus;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

	List<Book> findByBookStatus(BookStatus bookStatus);
	List<Book> findByTitle(String title);
	
	
}
