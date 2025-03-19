package com.CRUD.service;

import java.util.List;

import com.CRUD.entity.Department;

public interface DepartmentService {
	
	public Department post(Department department);
	
	public Department getbyid(long id);
	
	public List<Department> getdata();
	
	public void deletedata(long id);
	
}
