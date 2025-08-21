package com.codewithapp.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.codewithapp.blog.config.AppConstants;
import com.codewithapp.blog.entities.Role;
import com.codewithapp.blog.repositories.RoleRepo;
import java.util.List;

import  org.modelmapper.ModelMapper;

@SpringBootApplication
public class BlogAppApplication  implements   CommandLineRunner {
	@Autowired
   private   PasswordEncoder  passwordEncoder;
	
	 @Autowired
	 private  RoleRepo  roleRepo;
	 
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
@Bean
 public  ModelMapper  modelMapper() {
	return   new    ModelMapper();
}
@Override
public void run(String... args) throws Exception {
	// TODO Auto-generated method stub
	//System.out.println(this.passwordEncoder.encode("jkil")); 3
	//System.out.println(this.passwordEncoder.encode("ns9@%99"));  1
	System.out.println(this.passwordEncoder.encode("jkl")); //2
	try {
	Role   role=  new Role();
	role.setId(AppConstants.ADMIN_USER);//ADMIN
	role.setName("ADMIN_ROLE");
	
	Role   role1=  new Role();
	role1.setId(AppConstants.NORMAL_USER);//ADMIN
	role1.setName("NORMAL_ROLE");
	Role   role3=  new Role();
	role3.setId(AppConstants.NORMAL_USER);//ADMIN
	role3.setName("NORMAL_ROLE");
	List<Role> roles=List.of(role,role1,role3);
	List<Role> result=this.roleRepo.saveAll(roles);
	result.forEach(r->{
		System.out.println(r.getName());
	});
	
		
	}
	catch(Exception  e){
		//handle  excpetion krta   agr user h to 
		e.printStackTrace();
		
	}
	
}
}
