package com.CRUD.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.CRUD.entity.User;

public interface UserService {
	
	public User savedata(User user);
	
	public User getbyid(long id);

	 public User getByUsername(String username);
	 
	 public List<User> getdata();
	 
	 public void deletedata(long id);
	 
	
}
