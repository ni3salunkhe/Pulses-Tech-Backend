package com.CRUD.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRUD.entity.Branches;
import com.CRUD.repository.BranchesRepository;
import com.CRUD.service.BranchesService;

@Service
public class BranchesServiceImpl implements BranchesService{

	@Autowired
	private BranchesRepository branchesRepository;
	
	@Override
	public Branches post(Branches branhes) {
		// TODO Auto-generated method stub
		return branchesRepository.save(branhes);
	}

	@Override
	public List<Branches> getdata() {
		// TODO Auto-generated method stub
		return branchesRepository.findAll();
	}

	@Override
	public Branches getbyid(long id) {
		// TODO Auto-generated method stub
		return branchesRepository.findById(id).orElse(null);
	}

	@Override
	public void deletedata(long id) {
		// TODO Auto-generated method stub
		branchesRepository.deleteById(id);
	}

}
