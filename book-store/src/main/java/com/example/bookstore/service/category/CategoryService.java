package com.example.bookstore.service.category;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.bookstore.dto.ErrorCode;
import com.example.bookstore.exception.GenericException;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	public Category loadCategory(Long id) {
		return this.categoryRepository.findById(id).orElseThrow(() -> GenericException.builder()
				.errorCode(ErrorCode.NOT_FOUND)
				.errorMessage("Category not found")
				.httpStatus(HttpStatus.NOT_FOUND)
				.build());
	}
	
	
	public Category loadByName(String name) {
		return this.categoryRepository.findByName(name).orElseThrow(() -> GenericException.builder()
				.errorCode(ErrorCode.NOT_FOUND)
				.errorMessage("Category not found")
				.httpStatus(HttpStatus.NOT_FOUND)
				.build() );
	}
	
}
