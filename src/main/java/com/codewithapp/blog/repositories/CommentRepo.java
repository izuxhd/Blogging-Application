package com.codewithapp.blog.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithapp.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}