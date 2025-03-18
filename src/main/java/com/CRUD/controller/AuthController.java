package com.CRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.CRUD.dto.AuthenticationRequest;
import com.CRUD.dto.AuthenticationResponse;
import com.CRUD.service.UserService;
import com.CRUD.serviceImpl.UserServiceImpl;
import com.CRUD.util.JwtUtil;


@RestController
@RequestMapping("/api/auth")

public class AuthController {
	
	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private UserServiceImpl userServiceImpl;
	    
	    
	    @PostMapping(value="/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
	        );

	        final UserDetails userDetails = userServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
	        
	        // Get the user's role from authorities
	        String role = userDetails.getAuthorities().stream()
	                .findFirst()
	                .map(authority -> authority.getAuthority().replace("ROLE_", "").toLowerCase())
	                .orElse("user");

	        final String jwt = jwtUtil.generateToken(userDetails);

	        return ResponseEntity.ok(new AuthenticationResponse(jwt, role));
	    }
}
