package com.springboot.product.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.product.exception.ProductNotFoundByIdException;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> productNotFoundByIdException(ProductNotFoundByIdException e){
		ErrorStructure<String> errorStructure = new ErrorStructure<>();
		errorStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		errorStructure.setErrorMessage(e.getMessage());
		errorStructure.setData("Product Object With given Id Not Exist");
	return new ResponseEntity<ErrorStructure<String>>(errorStructure,HttpStatus.NOT_FOUND);
	}
}
