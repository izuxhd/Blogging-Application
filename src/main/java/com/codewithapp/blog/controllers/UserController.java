package com.codewithapp.blog.controllers;
import  com.codewithapp.blog.services.UserService;
import java.util.List;
import  com.codewithapp.blog.entities.User;
import  com.codewithapp.blog.payloads.UserDto;
import  com.codewithapp.blog.payloads.ApiResponse;

import org.springframework.http.HttpStatus;
import  org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import  org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/api/users")
@EnableGlobalMethodSecurity(prePostEnabled=true)

public class UserController {
	@Autowired
	private  UserService  userservice;
	//post-create
	
	//put   -updated
	 //delete -  delete  
	//get - get  user
	@PostMapping("/")
	public  ResponseEntity<UserDto> createUser(@RequestBody  UserDto  userDto){
		UserDto  createUserDto=this.userservice.createUser(userDto);
		return  new  ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Validated @RequestBody UserDto userDto, @PathVariable("userId") Integer uid)
	{	
		// agar naam same rakh rhe ho to pathVariable ke bracket me likhna jaroori nhi h.
		UserDto updatedUser = this.userservice.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	// DELETE - delete user
	// only admmin can delete user
	
	 // for role based authentication
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteuser(@PathVariable("userId") Integer uid)
	{
		this.userservice.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}
		
	// GET - get single user
	@PreAuthorize("hasRole('ADMIN_ROLE')")
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.userservice.getUserById(userId));
	}
	
	// GET - get all user
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return ResponseEntity.ok(this.userservice.getAllUsers());
	}

}
