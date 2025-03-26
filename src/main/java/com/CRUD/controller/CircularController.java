package com.CRUD.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.CRUD.dto.CircularDto;
import com.CRUD.entity.Circular;
import com.CRUD.entity.User;
import com.CRUD.service.BranchesService;
import com.CRUD.service.CircularService;
import com.CRUD.service.DepartmentService;
import com.CRUD.service.UserService;

import org.springframework.core.io.Resource;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class CircularController {

	private static final String DRAFTS_DIR = "uploads/drafts";
	private static final String PUBLISHED_DIR = "uploads/published";


	@Autowired
	private CircularService circularService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private BranchesService branchesService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/circular/create")
	public ResponseEntity<Circular> saveData(@ModelAttribute CircularDto circularDto) {
		try {
			// Ensure folder exists
			File directory = new File(DRAFTS_DIR);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			// Generate unique file name
			String fileName = System.currentTimeMillis() + "_" + circularDto.getFileurl().getOriginalFilename();
			Path filePath = Paths.get(DRAFTS_DIR, fileName);

			// Save file to folder
			Files.copy(circularDto.getFileurl().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			// Save file path in database
			Circular circular = new Circular();
			circular.setFileurl(fileName);
			circular.setPriority(circularDto.getPriority());
			circular.setSubject(circularDto.getSubject());
			circular.setIssuedDate(circularDto.getIssuedDate());
			circular.setStatus("under review");
			circular.setPublishedDate(null);
			circular.setComments(null);
			circular.setAdminviewed(false);
			circular.setBranches(branchesService.getbyid(circularDto.getBranchid()));
			circular.setDepartment(departmentService.getbyid(circularDto.getDepartmentid()));

			Long refId = circularDto.getReferncecircularid();
			if (refId != null) {
				Circular existingRefCircular = circularService.getbyid(refId);
				if (circularService.isCircularAlreadyReferenced(existingRefCircular)) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				circular.setReferncecircular(existingRefCircular);
			}

			Circular savedCircular = circularService.post(circular);
			return new ResponseEntity<>(savedCircular, HttpStatus.CREATED);

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	//---------------------------------------------------------------------------------------------
	
	// circular reject api for admin
	@PutMapping("circular/reject/{id}")
	public ResponseEntity<Circular> rejectcircular(@RequestBody CircularDto circularDto, @PathVariable long id) {
		Circular circular = circularService.getbyid(id);

		if (circular == null) {
			return new ResponseEntity<Circular>(HttpStatus.NOT_FOUND);
		}

		else {
			if (circular.getStatus().equals("Published")) {
				return new ResponseEntity<Circular>(HttpStatus.BAD_REQUEST);

			} else {
				circular.setStatus("Rejected");
				circular.setComments(circularDto.getComments());
				Circular savecircular = circularService.post(circular);
				return new ResponseEntity<Circular>(savecircular, HttpStatus.CREATED);
			}
		}
	}
	
	//------------------------------------------------------------------------------------------------
	
	//circular published API for admin
	@PutMapping("/circular/publish/{id}")
	public ResponseEntity<Circular> publishCircular(@RequestBody CircularDto circularDto, @PathVariable long id) {
		Circular circular = circularService.getbyid(id);
		if (circular == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		try {
			String filename = circular.getFileurl();
			Path oldPath = Paths.get(DRAFTS_DIR, filename);
			Path newPath = Paths.get(PUBLISHED_DIR, filename);

			// Ensure destination directory exists
			File publishedDir = new File(PUBLISHED_DIR);
			if (!publishedDir.exists()) {
				publishedDir.mkdirs();
			}

			// Move file from drafts to published
			Files.move(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);

//			
//			try {
//				// Correct icacls command
//				String denyDelete = "icacls \"" + newPath.toString()
//						+ "\" /inheritance:r /grant Everyone:R /deny Everyone:(D)";
//
//				// Execute command
//				Process denyProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", denyDelete });
//				denyProcess.waitFor();
//
//			} catch (IOException | InterruptedException e) {
//				e.printStackTrace();
//			}

//			DosFileAttributeView dosView = Files.getFileAttributeView(newPath, DosFileAttributeView.class);
//			dosView.setReadOnly(true);

			circular.setPublishedDate(circularDto.getPublishedDate());
			circular.setStatus("Published");
			circular.setComments(null);

			Circular savedCircular = circularService.post(circular);

			return new ResponseEntity<>(savedCircular, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//------------------------------------------------------------------------------------------------------
	//admin circular checked or not
	@PutMapping("/circular/viewed/{id}")
	public ResponseEntity<Circular> Circularchekedbyadmin(@PathVariable long id){
		
		Circular circular=circularService.getbyid(id);
		
		if(circular==null)
		{
		return new ResponseEntity<Circular>(HttpStatus.NOT_FOUND);	
		}
		
		else {
			circular.setAdminviewed(true);
			
			Circular saveCircular=circularService.post(circular);

			return new ResponseEntity<Circular>(saveCircular,HttpStatus.OK);	
		}
		
	}
	
	//------------------------------------------------------------------------------------------------------
	
	@PutMapping("/circular/{id}/userview/{userid}")
	public ResponseEntity<Circular> Circularviewedbyuser(@PathVariable long id,@PathVariable long userid){
			
		Circular circular=circularService.getbyid(id);
		
		User user=userService.getbyid(userid);
		
		if(circular==null && user==null)
		{
			return new ResponseEntity<Circular>(HttpStatus.NOT_FOUND);
		}
		else {
			Set<Long> viewedUsers=circular.getViewdByUserSet();
			if(!viewedUsers.contains(userid))
			{
				circular.addViewedByUser(userid);
				Circular saveCircular=circularService.post(circular);
				return new ResponseEntity<Circular>(saveCircular,HttpStatus.OK);
			}
			return new ResponseEntity<Circular>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//------------------------------------------------------------------------------------------------------
	// data get by id  API
	@GetMapping("/circular/{id}")
	public ResponseEntity<Circular> getByIdData(@PathVariable long id) {
		Circular circular = circularService.getbyid(id);
		if (circular != null) {
			return new ResponseEntity<>(circular, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	//---------------------------------------------------------------------------------------
	//file getting API
	@GetMapping("/circulars/published/{filename}")
	public ResponseEntity<Resource> getPublishedFile(@PathVariable String filename) {
	    try {
	        Path filePath = Paths.get(PUBLISHED_DIR, filename);
	        Resource resource = new UrlResource(filePath.toUri());

	        if (!resource.exists() || !resource.isReadable()) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	        }

	        // Determine content type
	        String contentType = determineContentType(filename);
	        
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
	                .contentType(MediaType.parseMediaType(contentType))
	                .body(resource);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	// Helper method to determine content type
	private String determineContentType(String filename) {
	    if (filename.toLowerCase().endsWith(".pdf")) {
	        return "application/pdf";
	    } else if (filename.toLowerCase().endsWith(".jpg") || 
	               filename.toLowerCase().endsWith(".jpeg")) {
	        return "image/jpeg";
	    } else if (filename.toLowerCase().endsWith(".png")) {
	        return "image/png";
	    } else if (filename.toLowerCase().endsWith(".doc")) {
	        return "application/msword";
	    } else if (filename.toLowerCase().endsWith(".docx")) {
	        return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	    }
	    // Default to octet-stream if type cannot be determined
	    return "application/octet-stream";
	}	

	
	@GetMapping("/circular")
	public ResponseEntity<List<Circular>> getData() {
		List<Circular> circulars = circularService.getdata();
		return new ResponseEntity<>(circulars, HttpStatus.OK);
	}

	
	
	@PutMapping("/circular/{id}")
	public ResponseEntity<Circular> updateCircular(@PathVariable long id, @ModelAttribute CircularDto circularDto) {
		Circular circular = circularService.getbyid(id);
		if (circular == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		try {
			// Check if a new file is provided
			MultipartFile file = circularDto.getFileurl();
			if (file != null && !file.isEmpty()) {
				// Generate new file path
				String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
				Path newFilePath = Paths.get(DRAFTS_DIR, fileName);

				// Save the new file
				Files.copy(file.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);

				// Delete old file if exists
				Path oldFilePath = Paths.get(DRAFTS_DIR, circular.getFileurl());
				if (Files.exists(oldFilePath)) {
					Files.delete(oldFilePath);
				}

				// Update file path in entity
				circular.setFileurl(fileName);
			}

			circular.setPriority(circularDto.getPriority());
			circular.setSubject(circularDto.getSubject());
			circular.setDepartment(departmentService.getbyid(circularDto.getDepartmentid()));

			Long refId = circularDto.getReferncecircularid();
			if (refId != null) {
				circular.setReferncecircular(circularService.getbyid(refId));
			}

			Circular updatedCircular = circularService.post(circular);
			return new ResponseEntity<>(updatedCircular, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/circular/{id}")
	public ResponseEntity<Void> deleteData(@PathVariable long id) {
		Circular circular = circularService.getbyid(id);
		if (circular != null) {
			// Delete the file from the system
			Path filePath = Paths.get(DRAFTS_DIR, circular.getFileurl());
			if (Files.exists(filePath)) {
				try {
					Files.delete(filePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			circularService.deletedata(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
