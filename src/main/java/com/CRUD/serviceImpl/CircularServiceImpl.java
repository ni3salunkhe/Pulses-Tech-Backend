package com.CRUD.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRUD.entity.Circular;
import com.CRUD.repository.CircularsRepository;
import com.CRUD.service.CircularService;

@Service
public class CircularServiceImpl implements CircularService{

	@Autowired
	private CircularsRepository circularsRepository;
	
	@Override
	public Circular post(Circular circular) {
		// TODO Auto-generated method stub
		return circularsRepository.save(circular);
	}

	@Override
	public List<Circular> getdata() {
		// TODO Auto-generated method stub
		return circularsRepository.findAll();
	}

	@Override
	public Circular getbyid(long id) {
		// TODO Auto-generated method stub
		return circularsRepository.findById(id).orElse(null);
	}

}
