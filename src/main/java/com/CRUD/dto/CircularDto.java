package com.CRUD.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CircularDto {
	
	private String subject;
    private long departmentid;
    private String priority;
    private MultipartFile fileurl;
    private Date issuedDate;
    private Date publishedDate;
    private String comments;
    private long branchid;
    private Long referncecircularid;
    
   
	
	
}
