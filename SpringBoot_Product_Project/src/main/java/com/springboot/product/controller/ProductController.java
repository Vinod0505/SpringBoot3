package com.springboot.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.product.model.Product;
import com.springboot.product.service.ProductService;
import com.springboot.product.utility.ResponseStructure;

@RestController("/")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@PostMapping("products/saveProduct")
	ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product p){
		return productService.saveProduct(p);
	}
	
	@GetMapping("products/findByProductId/{productId}")
	ResponseEntity<ResponseStructure<Product>> findByProductId(@PathVariable int productId){
		return productService.findByProductId(productId);
	}
	
	@PutMapping("products/updateProduct/{productId}")
	ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int productId,@RequestBody Product updatedProduct){
		return productService.updateProduct(productId,updatedProduct);
	}
	
	@DeleteMapping("products/deleteByProductId/{productId}")
	ResponseEntity<ResponseStructure<Product>> deleteByProductId(@PathVariable int productId){
		return productService.deleteByProductId(productId);
	}
	
	
	@GetMapping("products/findAllProducts")
	ResponseEntity<ResponseStructure<List<Product>>> findAllProducts(){
		return productService.findAllProducts();
	}
}
