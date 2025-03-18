package com.CRUD.dto;

import com.CRUD.entity.Circular;

public class CircularDto {
	
	private String subject;
	private String department;
	private String category;
	private String priority;
	private String fileurl;
	
	private Long referncecircularid;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public long getReferncecircularid() {
		return referncecircularid;
	}

	public void setReferncecircularid(long referncecircularid) {
		this.referncecircularid = referncecircularid;
	}
	
}
