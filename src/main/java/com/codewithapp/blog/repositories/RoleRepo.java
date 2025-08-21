package com.codewithapp.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithapp.blog.entities.Role;

public interface RoleRepo  extends  JpaRepository<Role,Integer> {

}
