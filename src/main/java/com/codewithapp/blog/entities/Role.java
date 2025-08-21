package com.codewithapp.blog.entities;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.GenerationType;
import  jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
	@Id
	
	 private  int  id;
	private   String name;
	  
	     
	

}
