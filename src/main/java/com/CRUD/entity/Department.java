package com.CRUD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int code;
	private String description;
	private String departmentName;
	private String status;
	
	@ManyToOne
    @JoinColumn(name = "categorId")
	private Category category;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(long id, int code, String description, String departmentName, String status, Category category) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.departmentName = departmentName;
		this.status = status;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", code=" + code + ", description=" + description + ", departmentName="
				+ departmentName + ", status=" + status + ", category=" + category + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}
