package com.jsp.springboot.actor.exception;

public class ActorsNotFoundException extends RuntimeException{

	private String message;

	public ActorsNotFoundException(String message) {
		this.message=message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
