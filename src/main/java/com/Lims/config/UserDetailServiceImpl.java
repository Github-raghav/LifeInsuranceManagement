package com.Lims.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Lims.dao.UserRepository;
import com.Lims.entities.User;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  // fetching user from database;
		System.out.println("username is "+  username);
	User user=userRepository.getUserByUserName(username);
	System.out.println("user is "+  user.getEmail());
	
	if(user.getEmail()==null) {
		throw new UsernameNotFoundException("Could not found user!!");
	}
	CustomUserDetails customerUserDetails=new CustomUserDetails(user);
	
	return customerUserDetails;
	}

}
