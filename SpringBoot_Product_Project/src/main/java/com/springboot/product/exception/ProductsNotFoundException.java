package com.springboot.product.exception;

public class ProductsNotFoundException extends RuntimeException{

	private String message;
	
	public ProductsNotFoundException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		
		return message;
	}
	
	
}
