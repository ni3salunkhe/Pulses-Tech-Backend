package com.CRUD.service;

import com.CRUD.entity.Department;

public interface DepartmentService {
	
	public Department post(Department department);
	
	public Department getbyid(long id);
	
}
