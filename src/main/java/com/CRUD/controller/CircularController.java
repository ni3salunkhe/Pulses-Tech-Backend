package com.CRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.dto.CircularDto;
import com.CRUD.entity.Circular;
import com.CRUD.service.CircularService;

@RestController
@RequestMapping("/api/auth")
public class CircularController {
	
	@Autowired
	private CircularService circularService;
	
	@PostMapping("/circular")
	public ResponseEntity<Circular> savedata(@RequestBody CircularDto circularDto)
	{
		Circular circular=new Circular();
		
		circular.setCategory(circularDto.getCategory());
		circular.setDepartment(circularDto.getDepartment());
		circular.setFileurl(circularDto.getFileurl());
		circular.setPriority(circularDto.getPriority());
		circular.setSubject(circularDto.getSubject());
		
		Long refId = circularDto.getReferncecircularid();
		if(refId != null) {
		    Circular referencedCircular = circularService.getbyid(refId);
		    circular.setReferncecircular(referencedCircular);
		}
		
		Circular saveCircular=circularService.post(circular);
		return new ResponseEntity<Circular>(circular,HttpStatus.OK);
	}
	
	@GetMapping("/circular/{id}")
	public ResponseEntity<Circular> getbyiddata(@PathVariable long id)
	{
		Circular circular=circularService.getbyid(id);
		return new ResponseEntity<Circular>(circular,HttpStatus.OK);
	}
	
}
