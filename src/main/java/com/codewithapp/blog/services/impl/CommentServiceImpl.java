package com.codewithapp.blog.services.impl;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithapp.blog.entities.Comment;
import com.codewithapp.blog.entities.Post;
import com.codewithapp.blog.exceptions.ResourceNotFoundException;
import com.codewithapp.blog.payloads.CommentDto;
import com.codewithapp.blog.repositories.CommentRepo;
import com.codewithapp.blog.repositories.PostRepo;
import com.codewithapp.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// kya comment karna , kis post pe comment karna h.
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		// 1st find the post 
				Post post = this.postRepo.findById(postId)
							.orElseThrow(()-> new ResourceNotFoundException("Post", "postId",postId));
		
		// is post pe hmko given 'comment' karna h. or comment ke under post ko add karna h.
		// phle dto se comment me convert karna hoga.
		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// 1st get the comment
		Comment com = commentRepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id",commentId));
		this.commentRepo.delete(com);
	}

}