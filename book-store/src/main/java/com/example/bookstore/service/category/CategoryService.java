package com.example.bookstore.service.category;

import org.springframework.stereotype.Service;

import com.example.bookstore.exception.NotFoundException;
import com.example.bookstore.model.Category;
import com.example.bookstore.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	public Category loadCategory(Long id) throws Exception {
		//return categoryRepository.findById(id).orElseThrow(Exception::new);
		return this.categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
	}
	
	
	public Category loadByName(String name) throws Exception{
		return this.categoryRepository.findByName(name).orElseThrow(() -> new NotFoundException("Category not found"));
	}
	
}
