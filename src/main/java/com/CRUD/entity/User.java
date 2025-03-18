package com.CRUD.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String Password;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private int departmentId;
	private int roleId;
	private Date createdAt;
	
	public User(long id, String username, String password, String fname, String lname, String email, String phone,
			int departmentId, int roleId, Date createdAt) {
		super();
		this.id = id;
		this.username = username;
		Password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.departmentId = departmentId;
		this.roleId = roleId;
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", Password=" + Password + ", fname=" + fname + ", lname="
				+ lname + ", email=" + email + ", phone=" + phone + ", departmentId=" + departmentId + ", roleId="
				+ roleId + ", createdAt=" + createdAt + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
		
}
