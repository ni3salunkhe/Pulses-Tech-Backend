package com.CRUD.entity;

import com.CRUD.enums.Permissions;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String roleName;
	
	private String description;
	
	private String status;
	
	private Permissions permissions;

	@ManyToOne
	@JoinColumn(name = "departmentId")
	private Department departent;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(long id, String roleName, String description, String status, Permissions permissions,
			Department departent) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.description = description;
		this.status = status;
		this.permissions = permissions;
		this.departent = departent;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", description=" + description + ", status=" + status
				+ ", permissions=" + permissions + ", departent=" + departent + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

	public Department getDepartent() {
		return departent;
	}

	public void setDepartent(Department departent) {
		this.departent = departent;
	}

	
}
