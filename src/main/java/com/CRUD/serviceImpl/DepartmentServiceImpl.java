package com.CRUD.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRUD.entity.Department;
import com.CRUD.repository.DepartmentRepository;
import com.CRUD.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department post(Department department) {
		
		return departmentRepository.save(department);
	}

	@Override
	public Department getbyid(long id) {
		
		return departmentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Department> getdata() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public void deletedata(long id) {
		// TODO Auto-generated method stub
		departmentRepository.deleteById(id);
	}

}
