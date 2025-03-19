package com.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		department.setDepartmentName(departmentDto.getDepartmentName());
		department.setStatus(departmentDto.getStatus());
		department.setCategory(categoryService.getbyid(departmentDto.getCategoryid()));
		Department saveDepartment=departmentService.post(department);
		return new ResponseEntity<Department>(saveDepartment,HttpStatus.OK);
	}
	
	@GetMapping("/department")
	public ResponseEntity<List<Department>> getalldata(){
		List<Department> department=departmentService.getdata();
		return new ResponseEntity<List<Department>>(department,HttpStatus.OK);
	}
	
	@PutMapping("/department/{id}")
	public ResponseEntity<Department> putdata(@PathVariable long id,@RequestBody DepartmentDto departmentDto)
	{
		Department department=departmentService.getbyid(id);
		if(department== null)
		{
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}

		else {
			department.setCode(departmentDto.getCode());
			department.setDescription(departmentDto.getDescription());
			department.setDepartmentName(departmentDto.getDepartmentName());
			department.setStatus(departmentDto.getStatus());
			department.setCategory(categoryService.getbyid(departmentDto.getCategoryid()));
			Department saveDepartment=departmentService.post(department);
			return new ResponseEntity<Department>(saveDepartment,HttpStatus.CREATED);
		}
	}
	
	
	@DeleteMapping("/department/{id}")
	public ResponseEntity<Void> deletedata(@PathVariable long id)
	{
		departmentService.deletedata(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/department/{id}")
	public ResponseEntity<Department> getbyiddata(@PathVariable long id)
	{
		Department department=departmentService.getbyid(id);
		return new ResponseEntity<Department>(department,HttpStatus.OK);
	}
}
