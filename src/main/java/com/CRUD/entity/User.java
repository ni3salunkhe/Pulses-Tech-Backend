package com.CRUD.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
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
	@ManyToOne
	@JoinColumn(name = "branchid")
	private Branches branch;
	@ManyToOne
	@JoinColumn(name = "departmentid")
	private Department departmentId;
	@ManyToOne
	@JoinColumn(name="roleid")
	private Role roleId;
	private Date createdAt;
	
		
}
