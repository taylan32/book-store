package com.example.bookstore.repository;

import com.example.bookstore.model.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

	Optional<Category> findByName(String name);
	
}
