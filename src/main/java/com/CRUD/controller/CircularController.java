package com.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.CRUD.dto.CircularDto;
import com.CRUD.entity.Circular;
import com.CRUD.service.CircularService;
import com.CRUD.service.DepartmentService;

@RestController
@RequestMapping("/api/auth")
public class CircularController {

    @Autowired
    private CircularService circularService;

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/circular")
    public ResponseEntity<Circular> saveData(@RequestBody CircularDto circularDto) {
        Circular circular = new Circular();
        
        circular.setFileurl(circularDto.getFileurl());
        circular.setPriority(circularDto.getPriority());
        circular.setSubject(circularDto.getSubject());
       
        circular.setDepartment(departmentService.getbyid(circularDto.getDepartmentid()));

        Long refId = circularDto.getReferncecircularid();
        if (refId != null) {
            
        	Circular existingRefCircular =circularService.getbyid(refId);
        	
        	if(circularService.isCircularAlreadyReferenced(existingRefCircular))
        	{
        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        	}
        	
            circular.setReferncecircular(circularService.getbyid(refId));
        }

        Circular savedCircular = circularService.post(circular);
        return new ResponseEntity<>(savedCircular, HttpStatus.CREATED);
    }

       

    @GetMapping("/circular/{id}")
    public ResponseEntity<Circular> getbyiddata(@PathVariable long id) {
        Circular circular = circularService.getbyid(id);
        if (circular != null) {
            return new ResponseEntity<>(circular, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/circular")
    public ResponseEntity<List<Circular>> getdata()
    {
    	List<Circular> circular=circularService.getdata();
    	return new ResponseEntity<List<Circular>>(circular,HttpStatus.OK);
    }
    
    @PutMapping("/circular/{id}")
    public ResponseEntity<Circular> putdata(@PathVariable long id,@RequestBody CircularDto circularDto)
    {
    	Circular circular=circularService.getbyid(id);
    	if(circular==null)
    	{
    		return new ResponseEntity<Circular>(HttpStatus.NOT_FOUND);
    	}
    	else {
    		 circular.setFileurl(circularDto.getFileurl());
    	        circular.setPriority(circularDto.getPriority());
    	        circular.setSubject(circularDto.getSubject());
    	       
    	        circular.setDepartment(departmentService.getbyid(circularDto.getDepartmentid()));

    	        Long refId = circularDto.getReferncecircularid();
    	        if (refId != null) {
    	            
    	            circular.setReferncecircular(circularService.getbyid(circularDto.getReferncecircularid()));
    	        }

    	        Circular savedCircular = circularService.post(circular);
    	        return new ResponseEntity<>(savedCircular, HttpStatus.CREATED);
    	}
    }
    
    @DeleteMapping("/circular/{id}")
    public ResponseEntity<Void> deletedata(@PathVariable long id)
    {
    	circularService.deletedata(id);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
