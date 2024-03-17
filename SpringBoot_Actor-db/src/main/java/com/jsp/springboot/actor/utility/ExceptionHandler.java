package com.jsp.springboot.actor.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.springboot.actor.exception.ActorNotFoundByIdException;
import com.jsp.springboot.actor.exception.ActorsNotFoundException;

@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> actorNotFoundByIdException(ActorNotFoundByIdException ex){
		
		ErrorStructure<String> errorStructure = new ErrorStructure<>();
		errorStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		errorStructure.setErrorMessage(ex.getMessage());
		errorStructure.setData("Actor Object with the given Id not Exist");
		
		return new ResponseEntity<ErrorStructure<String>>(errorStructure,HttpStatus.NOT_FOUND);
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> actorsNotFoundException(ActorsNotFoundException ex){
		
		ErrorStructure<String> errorStructure = new ErrorStructure<>();
		errorStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		errorStructure.setErrorMessage(ex.getMessage());
		errorStructure.setData("Actor objects Not found ");
		
		return new ResponseEntity<ErrorStructure<String>>(errorStructure,HttpStatus.NOT_FOUND);
		
	}
	
}
