package com.CRUD.service;

import com.CRUD.entity.Category;

public interface CategoryService {
	
	public Category post(Category category);
	
	public Category getbyid(long id);
	
}
