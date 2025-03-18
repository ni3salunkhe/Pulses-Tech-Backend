package com.CRUD.dto;

import com.CRUD.entity.Department;
import com.CRUD.enums.Permissions;

public class RoleDto {
	
private String roleName;
	
	private String description;
	
	private String status;
	
	private Permissions permissions;

	private long departentid;

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

	public long getDepartentid() {
		return departentid;
	}

	public void setDepartentid(long departentid) {
		this.departentid = departentid;
	}

		
}
