package com.CRUD.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {
	
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private long departmentId;
	private long roleId;
	private long branchid;
	private Date createdAt;
	
	
	
}
