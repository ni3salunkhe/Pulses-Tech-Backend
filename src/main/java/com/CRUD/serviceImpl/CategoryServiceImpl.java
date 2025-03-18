package com.CRUD.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRUD.entity.Category;
import com.CRUD.repository.CategoryRepository;
import com.CRUD.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category post(Category category) {
		
		return categoryRepository.save(category);
	}

	@Override
	public Category getbyid(long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElse(null);
	}

}
