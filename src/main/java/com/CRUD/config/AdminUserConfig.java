package com.CRUD.config;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.CRUD.entity.User;
import com.CRUD.service.UserService;


@Configuration
public class AdminUserConfig {
		
	 @Bean
	    public CommandLineRunner addAdminUser(UserService userService) {
	        return args -> {
	            // Check if admin user already exists
	            if (userService.getByUsername("admin") == null) {
	                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	                String encodedPassword = passwordEncoder.encode("adminpassword");

	                // Create admin user
	                User admin = new User();
	                admin.setUsername("admin");
	                admin.setPassword(encodedPassword);
	                admin.setEmail("admin@example.com");
	                admin.setPhone("123-456-7890");
	                admin.setDepartmentId(1); // Assign department ID as needed
	                admin.setRoleId(1); // Assuming 1 is for the ADMIN role
	                admin.setCreatedAt(new Date());

	                // Save the admin user to the database
	                userService.savedata(admin);
	            }
	        };
	    }

	
}
