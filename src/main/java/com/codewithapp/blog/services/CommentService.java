package com.codewithapp.blog.services;



import com.codewithapp.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto , Integer postId);
	
	void deleteComment(Integer commentId);

}