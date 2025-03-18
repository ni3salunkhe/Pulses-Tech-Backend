package com.CRUD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String categoryName;
	private String description;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(long id, String categoryName, String description) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", description=" + description + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
