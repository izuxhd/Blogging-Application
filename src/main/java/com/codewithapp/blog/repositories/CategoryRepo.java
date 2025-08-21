package com.codewithapp.blog.repositories;




import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithapp.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
