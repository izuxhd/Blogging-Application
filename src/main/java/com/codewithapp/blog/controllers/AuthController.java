package com.codewithapp.blog.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import  org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithapp.blog.payloads.JwtAuthResponse;
import com.codewithapp.blog.security.JwtTokenHelper;
import com.codewithapp.blog.exceptions.ApiExceptions;
import com.codewithapp.blog.payloads.JwtAuthRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
 
 @RestController
 @RequestMapping("/api/v1/auth/")

public class AuthController {
	 
	   @Autowired
	   private   JwtTokenHelper  jwtTokenHelper;
	   @Autowired
	     private  UserDetailsService  userDetailsService ;
	   @Autowired
	     private AuthenticationManager  authenticationManager;
	 
	 
	 
	 @PostMapping("/login")
	 public   ResponseEntity<JwtAuthResponse>  createToken(  @RequestBody JwtAuthRequest  request) throws Exception{
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails  userDetails =this.userDetailsService.loadUserByUsername(request.getUsername());
		 String  token=this.jwtTokenHelper.generateToken(userDetails) ;
		 JwtAuthResponse  response=new JwtAuthResponse();
		 response.setToken(token);
		   return  new  ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		 
	 }
	 
	 
	 private  void   authenticate(String  username,String  password) throws Exception {
		 UsernamePasswordAuthenticationToken   usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,password);
		  
		try { this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);}
		catch(BadCredentialsException e){
			  System.out.print("Invalid  Details !");
			    throw  new   ApiExceptions("Invalid   Username  or   Password");
			
		}
		   
		  
	 }
	 

}
