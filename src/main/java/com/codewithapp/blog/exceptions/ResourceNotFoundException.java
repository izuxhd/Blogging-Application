package com.codewithapp.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException  extends   RuntimeException {
	String   resourcename;
	String fieldname;
	long filedvalue;
	public ResourceNotFoundException(String   resourcename,String fieldname,long filedvalue) {
		super(String.format("%s  not  found   with  %s :   %d",resourcename,fieldname,filedvalue));
		this.resourcename=resourcename;
		this.fieldname=fieldname;
		this.filedvalue=filedvalue;
	}
	

}
