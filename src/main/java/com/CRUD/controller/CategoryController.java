package com.CRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.dto.CategoryDto;
import com.CRUD.entity.Category;
import com.CRUD.service.CategoryService;

@RestController
@RequestMapping("/api/auth")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category")
	public ResponseEntity<Category> savedata(@RequestBody CategoryDto categoryDto)
	{
		Category category=new Category();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setDescription(categoryDto.getDescription());
		Category saveCategory=categoryService.post(category);
		return new ResponseEntity<Category>(saveCategory,HttpStatus.OK);
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getbyiddata(@PathVariable Long id)
	{
		Category category=categoryService.getbyid(id);
		return new ResponseEntity<Category>(category,HttpStatus.OK);
	}
}
