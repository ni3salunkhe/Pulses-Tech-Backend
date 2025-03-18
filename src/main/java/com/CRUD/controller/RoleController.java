package com.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.dto.RoleDto;
import com.CRUD.entity.Role;
import com.CRUD.service.DepartmentService;
import com.CRUD.service.RoleService;

@RestController
@RequestMapping("/api/auth")
public class RoleController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/role")
	public ResponseEntity<Role> savedata(@RequestBody RoleDto roleDto){
		
		Role role=new Role();
		role.setRoleName(roleDto.getRoleName());
		role.setPermissions(roleDto.getPermissions());
		role.setDescription(roleDto.getDescription());
		role.setStatus(roleDto.getStatus());
		System.out.println(roleDto.getStatus());
		role.setDepartent(departmentService.getbyid(roleDto.getDepartentid()));
		Role saveRole=roleService.post(role);
		return new ResponseEntity<Role>(saveRole,HttpStatus.OK);
	}
	
	@GetMapping("/getrole")
	public ResponseEntity<List<Role>> getalldata()
	{
		List<Role> role=roleService.getdata();
		return new ResponseEntity<List<Role>>(role,HttpStatus.OK);
	}
	
	@GetMapping("/getrole/{id}")
	public ResponseEntity<Role> getbyiddata(@PathVariable long id)
	{
		
		Role role=roleService.getbyid(id);
		return new ResponseEntity<Role>(role,HttpStatus.OK);
	}
}
