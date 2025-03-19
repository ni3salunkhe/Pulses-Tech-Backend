package com.CRUD.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Circular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String subject;

    @ManyToOne
    @JoinColumn(name = "departmentid")  // Foreign key to Department
    private Department department;

    private String priority;
    private String fileurl;
    @ManyToOne(fetch = FetchType.EAGER)
    private Circular referncecircular;
    
	public Circular() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Circular(long id, String subject, Department department, String priority, String fileurl,
			Circular referncecircular) {
		super();
		this.id = id;
		this.subject = subject;
		this.department = department;
		this.priority = priority;
		this.fileurl = fileurl;
		this.referncecircular = referncecircular;
	}
	@Override
	public String toString() {
		return "Circular [id=" + id + ", subject=" + subject + ", department=" + department + ", priority=" + priority
				+ ", fileurl=" + fileurl + ", referncecircular=" + referncecircular + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
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
	public Circular getReferncecircular() {
		return referncecircular;
	}
	public void setReferncecircular(Circular referncecircular) {
		this.referncecircular = referncecircular;
	}
    
	
 
}
