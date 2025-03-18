package com.CRUD.service;

import java.util.List;

import com.CRUD.entity.Role;

public interface RoleService {
	
	public Role post(Role role);
	
	public List<Role> getdata();
	
	public Role getbyid(long id);
	
}
