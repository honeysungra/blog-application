package com.springboot.blogapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private String resourceName;
	private String resourceValue;
	private long fieldValue;
	
	public ResourceNotFoundException(String resourceName, String resourceValue, long fieldValue) {
		super(String.format("%s is not found with %s : '%s' ", resourceName, resourceValue, fieldValue));
		this.resourceName = resourceName;
		this.resourceValue = resourceValue;
		this.fieldValue = fieldValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getResourceValue() {
		return resourceValue;
	}

	public long getFieldValue() {
		return fieldValue;
	}
	
	
	
}
