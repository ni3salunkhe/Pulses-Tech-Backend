package com.CRUD.entity;

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
	private String department;
	private String category;
	private String priority;
	private String fileurl;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referncecircular")
    private Circular referncecircular;

	public Circular() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Circular(long id, String subject, String department, String category, String priority, String fileurl,
			Circular referncecircular) {
		super();
		this.id = id;
		this.subject = subject;
		this.department = department;
		this.category = category;
		this.priority = priority;
		this.fileurl = fileurl;
		this.referncecircular = referncecircular;
	}

	@Override
	public String toString() {
		return "Circular [id=" + id + ", subject=" + subject + ", department=" + department + ", category=" + category
				+ ", priority=" + priority + ", fileurl=" + fileurl + ", referncecircular=" + referncecircular + "]";
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

	public Circular getReferncecircular() {
		return referncecircular;
	}

	public void setReferncecircular(Circular referncecircular) {
		this.referncecircular = referncecircular;
	}
	
}
