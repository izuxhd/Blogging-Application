package com.codewithapp.blog.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithapp.blog.entities.Role;
import com.codewithapp.blog.entities.User;
import com.codewithapp.blog.payloads.UserDto;
import com.codewithapp.blog.repositories.RoleRepo;
import com.codewithapp.blog.repositories.UserRepository;
import com.codewithapp.blog.services.UserService;
 import  com.codewithapp.blog.exceptions.*;
  import com.codewithapp.blog.config.AppConstants;
  
 
 @Service

public class UserServiceimpl implements UserService  {
	@Autowired
	private UserRepository  userRepo;
	@Autowired
	  private   ModelMapper  modelMapper;
	@Autowired
	  private  PasswordEncoder  passwordEncoder ;
@Autowired
private  RoleRepo  roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User  user=this.dtoToUser(userDto);
		User  saveduser=this.userRepo.save(user);
		
		return this.userToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		// TODO Auto-generated method stub
		User  user=this.userRepo.findById(id).orElseThrow(()->new  ResourceNotFoundException("User "," Id",id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User  updateduser= this.userRepo.save(user);
		 UserDto  userDto1=this.userToDto(updateduser);
		 return  userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User  user=this.userRepo.findById(userId).orElseThrow(()->new  ResourceNotFoundException("User "," Id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
		  User  user=this.userRepo.findById(userId).orElseThrow(()->new  ResourceNotFoundException("User "," Id",userId));
		  this.userRepo.delete(user);

	}
	private  User  dtoToUser(UserDto  userDto) {
//		User  user =new  User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		User  user =this.modelMapper.map(userDto, User.class);
		return user;
	}
	private  UserDto userToDto(User  user) {
//		UserDto  userDto=new  UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		//model mapper
		UserDto  userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		// TODO Auto-generated method stub
		   User  user=this.modelMapper.map(userDto,User.class);
		  user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		    Role   role=this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		    user.getRoles().add(role);
		    User newUser = this.userRepo.save(user);
		   
		  
		return this.modelMapper.map(newUser, UserDto.class);
		
		
	}

}
