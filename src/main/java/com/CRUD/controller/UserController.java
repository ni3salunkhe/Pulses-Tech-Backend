package com.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.dto.UserDto;
import com.CRUD.entity.User;
import com.CRUD.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	private UserService userService;
	
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
		user.setDepartmentId(userDto.getDepartmentId());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setRoleId(userDto.getRoleId());
		
		User saveuser=userService.savedata(user);
		return new ResponseEntity<User>(saveuser,HttpStatus.OK);
	}
	
	@GetMapping("/getusers")
	public ResponseEntity<List<User>> getalldata()
	{
		List<User> user=userService.getdata();
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}
	
}
