package com.CRUD.service;

import java.util.List;

import com.CRUD.entity.Category;

public interface CategoryService {
	
	public Category post(Category category);
	
	public Category getbyid(long id);
	
	public List<Category> getdata();
	
	public void deletedata(long id);
	
}
