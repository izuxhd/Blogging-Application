package com.codewithapp.blog.services;
import  com.codewithapp.blog.payloads.UserDto;
import  java.util.List;

public interface UserService {
	UserDto registerNewUser(UserDto  user);

	UserDto  createUser(UserDto  user) ;
	UserDto  updateUser(UserDto  user,Integer  id);
	UserDto  getUserById(Integer   userId);
	List<UserDto>getAllUsers();

	void deleteUser(Integer  userId);
		
		
	
}
