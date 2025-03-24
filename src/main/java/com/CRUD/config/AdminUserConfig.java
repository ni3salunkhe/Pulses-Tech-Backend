package com.CRUD.config;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.CRUD.entity.Role;
import com.CRUD.entity.User;
import com.CRUD.enums.Permissions;
import com.CRUD.service.RoleService;
import com.CRUD.service.UserService;


@Configuration
public class AdminUserConfig {
		
	 @Bean
	    public CommandLineRunner addAdminUser(UserService userService,RoleService roleService) {
	        return args -> {
	            // Check if admin user already exists
	            if (userService.getByUsername("admin") == null) {
	                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	                String encodedPassword = passwordEncoder.encode("adminpassword");
	                
	                Role adminrole=new Role();
	                adminrole.setPermissions(Permissions.System);
	                adminrole.setRoleName("Admin");
	                
	                roleService.post(adminrole);
	                
	                Role clerkrole=new Role();
	                clerkrole.setPermissions(Permissions.Create);
	                clerkrole.setRoleName("Clerk");
	                
	                roleService.post(clerkrole);
	                
	                Role userRole=new Role();
	                userRole.setPermissions(Permissions.User);
	                userRole.setRoleName("User");
	                roleService.post(userRole);
	                
	                // Create admin user
	                User admin = new User();
	                admin.setUsername("admin");
	                admin.setPassword(encodedPassword);
	                admin.setEmail("admin@example.com");
	                admin.setPhone("123-456-7890");
	                admin.setDepartmentId(null); // Assign department ID as needed
	                admin.setRoleId(adminrole); // Assuming 1 is for the ADMIN role
	                admin.setBranch(null);
	                admin.setCreatedAt(new Date());

	                // Save the admin user to the database
	                userService.savedata(admin);
	            }
	        };
	    }

	
}
