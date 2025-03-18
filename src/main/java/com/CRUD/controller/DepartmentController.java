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

import com.CRUD.dto.DepartmentDto;
import com.CRUD.entity.Department;
import com.CRUD.service.CategoryService;
import com.CRUD.service.DepartmentService;

@RestController
@RequestMapping("/api/auth")
public class DepartmentController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/department")
	public ResponseEntity<Department> savedata(@RequestBody DepartmentDto departmentDto)
	{
		Department department=new Department();
		department.setCode(departmentDto.getCode());
		department.setDescription(departmentDto.getDescription());
		department.setStatus(departmentDto.getStatus());
		department.setCategory(categoryService.getbyid(departmentDto.getCategoryid()));
		Department saveDepartment=departmentService.post(department);
		return new ResponseEntity<Department>(saveDepartment,HttpStatus.OK);
	}
	
	@GetMapping("/department/{id}")
	public ResponseEntity<Department> getbyiddata(@PathVariable long id)
	{
		Department department=departmentService.getbyid(id);
		return new ResponseEntity<Department>(department,HttpStatus.OK);
	}
}
