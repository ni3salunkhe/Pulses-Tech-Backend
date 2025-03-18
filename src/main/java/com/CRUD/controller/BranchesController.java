package com.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.dto.BranchesDto;
import com.CRUD.entity.Branches;
import com.CRUD.service.BranchesService;

@RestController
@RequestMapping("/api/auth")
public class BranchesController {
	
	@Autowired
	private BranchesService branchesService;
	
	@GetMapping("/branches")
	public ResponseEntity<List<Branches>> getalldata()
	{
		List<Branches> branches=branchesService.getdata();
		return new ResponseEntity<List<Branches>>(branches,HttpStatus.OK);
	}
	
	@PostMapping("/branches")
	public ResponseEntity<Branches> savedata(@RequestBody BranchesDto branchesDto)
	{
		Branches branches=new Branches();
		branches.setBranchname(branchesDto.getBranchname());
		branches.setAddress(branchesDto.getAddress());
		branches.setCode(branchesDto.getCode());
		branches.setRegion(branchesDto.getRegion());
		branches.setStatus(branchesDto.getStatus());
		
		Branches saveBranches=branchesService.post(branches);
		return new ResponseEntity<Branches>(saveBranches,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Branches> getdatabyid(@PathVariable long id)
	{
		Branches branches=branchesService.getbyid(id);
		
		return new ResponseEntity<Branches>(branches,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Branches> putdata(@PathVariable long id,@RequestBody BranchesDto branchesDto)
	{
		Branches branches=branchesService.getbyid(id);
		if(branches ==null)
		{
			return new ResponseEntity<Branches>(HttpStatus.NOT_FOUND);
		}
		else {
			
			branches.setBranchname(branchesDto.getBranchname());
			branches.setAddress(branchesDto.getAddress());
			branches.setCode(branchesDto.getCode());
			branches.setRegion(branchesDto.getRegion());
			branches.setStatus(branchesDto.getStatus());
			Branches saveBranches=branchesService.post(branches);
			return new ResponseEntity<Branches>(saveBranches,HttpStatus.CREATED);
		}
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletedata(@PathVariable long id)
	{
		branchesService.deletedata(id);
				
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
