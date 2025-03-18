package com.CRUD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Branches {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String branchname;
	private int code;
	private String address;
	private String region;
	private String status;
	
	public Branches() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Branches(long id, String branchname, int code, String address, String region, String status) {
		super();
		this.id = id;
		this.branchname = branchname;
		this.code = code;
		this.address = address;
		this.region = region;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Branches [id=" + id + ", branchname=" + branchname + ", code=" + code + ", address=" + address
				+ ", region=" + region + ", status=" + status + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
