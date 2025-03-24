package com.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.dto.UserDto;
import com.CRUD.entity.User;
import com.CRUD.service.BranchesService;
import com.CRUD.service.DepartmentService;
import com.CRUD.service.RoleService;
import com.CRUD.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BranchesService branchesService;
	
	private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@PostMapping("/register")
	public ResponseEntity<User> savedata(@RequestBody UserDto userDto)
	{
		User user=new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setFname(userDto.getFname());
		user.setLname(userDto.getLname());
		user.setCreatedAt(userDto.getCreatedAt());
		user.setDepartmentId(departmentService.getbyid(userDto.getDepartmentId()));
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setBranch(branchesService.getbyid(userDto.getBranchid()));
		user.setRoleId(roleService.getbyid(userDto.getRoleId()));
		
		User saveuser=userService.savedata(user);
		return new ResponseEntity<User>(saveuser,HttpStatus.OK);
	}
	
	@GetMapping("/getusers")
	public ResponseEntity<List<User>> getalldata()
	{
		List<User> user=userService.getdata();
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}
	
	@GetMapping("/getusers/{id}")
	public ResponseEntity<User> getbyiddata(@PathVariable long id)
	{
		User user=userService.getbyid(id);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PutMapping("/edituser/{id}")
	public ResponseEntity<User> putdata(@PathVariable long id,@RequestBody UserDto userDto)
	{
		User user=userService.getbyid(id);
		if(user==null)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		else {
			user.setUsername(userDto.getUsername());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			user.setFname(userDto.getFname());
			user.setLname(userDto.getLname());
			user.setCreatedAt(userDto.getCreatedAt());
			user.setDepartmentId(departmentService.getbyid(userDto.getDepartmentId()));
			user.setEmail(userDto.getEmail());
			user.setPhone(userDto.getPhone());
			user.setRoleId(roleService.getbyid(userDto.getRoleId()));
			user.setBranch(branchesService.getbyid(userDto.getBranchid()));
			User saveuser=userService.savedata(user);
			return new ResponseEntity<User>(saveuser,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletedata(@PathVariable long id)
	{
	
		userService.deletedata(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
