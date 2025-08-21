package com.codewithapp.blog.security;
import com.codewithapp.blog.entities.User;
import  com.codewithapp.blog.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codewithapp.blog.repositories.UserRepository;

@Service
public class CutomUserDetailService  implements  UserDetailsService {
	@Autowired
	private UserRepository  userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// loading user  from database  by username
		User  user=this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user" ,"email: "+username ,0));
		return  user;
		
		
	}

}
