package com.springboot.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springboot.product.model.Product;
import com.springboot.product.utility.ResponseStructure;

public interface ProductService {

	ResponseEntity<ResponseStructure<Product>> saveProduct(Product p);

	ResponseEntity<ResponseStructure<Product>> findByProductId(int productId);

	ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, Product updatedProduct);

	ResponseEntity<ResponseStructure<Product>> deleteByProductId(int productId);

	ResponseEntity<ResponseStructure<List<Product>>> findAllProducts();

	
}
