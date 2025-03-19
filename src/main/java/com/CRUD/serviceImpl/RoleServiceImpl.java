package com.CRUD.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRUD.entity.Role;
import com.CRUD.repository.RoleRepository;
import com.CRUD.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role post(Role role) {
		
		return roleRepository.save(role);
	}

	@Override
	public List<Role> getdata() {
		
		return roleRepository.findAll();
	}

	@Override
	public Role getbyid(long id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id).orElse(null);
	}

	@Override
	public void deletedata(long id) {
		// TODO Auto-generated method stub
		roleRepository.deleteById(id);
	}

}
