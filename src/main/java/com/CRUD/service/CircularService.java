package com.CRUD.service;

import java.util.List;

import com.CRUD.entity.Circular;

public interface CircularService {
	
	public Circular post(Circular circular);
	
	public List<Circular> getdata();
	
	public Circular getbyid(long id);
	
	public void deletedata(long id);
	
	 public boolean isCircularAlreadyReferenced(Circular circular);
}
