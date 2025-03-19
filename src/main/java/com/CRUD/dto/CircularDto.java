package com.CRUD.dto;



public class CircularDto {
	
	private String subject;
    private long departmentid;
    private String priority;
    private String fileurl;
    private Long referncecircularid;
    
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public long getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(long departmentid) {
		this.departmentid = departmentid;
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
	public Long getReferncecircularid() {
		return referncecircularid;
	}
	public void setReferncecircularid(Long referncecircularid) {
		this.referncecircularid = referncecircularid;
	}
    
	
	
}
