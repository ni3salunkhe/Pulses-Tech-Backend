package com.CRUD.entity;

import java.sql.Date;

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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
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
    private Date issuedDate;
    private Date publishedDate;
    private String status;
    private String comments;
    
    @ManyToOne
    @JoinColumn(name="branchesid")
    private Branches branches;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Circular referncecircular;
    
  
	
 
}
