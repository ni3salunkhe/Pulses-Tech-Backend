package com.CRUD.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CRUD.entity.Role;
import com.CRUD.entity.User;
import com.CRUD.repository.UserRepository;
import com.CRUD.service.RoleService;
import com.CRUD.service.UserService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserDetailsService ,UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	 
	@Override
	public User savedata(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepository.findByUsername(username);
	    if (user == null) {
	        throw new UsernameNotFoundException("User not found with username: " + username);
	    }

	    Role userRole = roleService.getbyid(user.getRoleId());
	   
	    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + (userRole.getRoleName().toUpperCase()));

	    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), java.util.Collections.singletonList(authority));
	}

	@Override
	public List<User> getdata() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void deletedata(long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public User getbyid(long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}


	
	
}
