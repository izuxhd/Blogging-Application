package com.codewithapp.blog.security;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class JwtAuthencticationFilter  extends   OncePerRequestFilter {
	@Autowired
	private  UserDetailsService userDetailsService;
	@Autowired
	private  JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		   String   requestTokens= request.getHeader("Authorization");
		   System.out.println(requestTokens);
		   String username=null;
		   String     token=null;
		     if(requestTokens!=null    && requestTokens.startsWith("Bearer "))
		     {
		    	  token=requestTokens.substring(7);
		    	  try {
		    	  username=this.jwtTokenHelper.getUsername(token);
		    	  }
		    	  catch(ExpiredJwtException e) {
		    		  System.out.println("Uanble  to  get jwt  token");
		    	  }
		    	  catch(MalformedJwtException  e) {
		    		  System.out.println("invalid  jwt");
		    		  
		    	  }
		     }
		     else{
		    	 System.out.println("  jwt  token doesn't begin with  Bearere"); 
		    	 }
		     //validate   token   krna h
		     if(username !=null  &&  SecurityContextHolder.getContext().getAuthentication()==null) {
		    	 UserDetails  userDetails=this.userDetailsService.loadUserByUsername(username);
		    	   if(this.jwtTokenHelper.validateToken(token, userDetails)) {
		    		   //shi chla  mola
		    		   //authentication
		    		    UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
		    		    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    		    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		    	   }
		    	   else {
		    		   System.out.println("invalid  jwt  token");
		    	   }
		    	 
		     }
		     else {
		    	 System.out.println("username is  null  or context is null");
		    	 
		     }
		     filterChain.doFilter(request, response);
		    	 
		    	 
		     
		   
		
		
	}

}
