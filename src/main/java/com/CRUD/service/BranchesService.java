package com.CRUD.service;

import java.util.List;

import com.CRUD.entity.Branches;

public interface BranchesService {
	
	public Branches post(Branches branhes);
	
	public List<Branches> getdata();
	
	public Branches getbyid(long id);
	
	public void deletedata(long id);
	
}
