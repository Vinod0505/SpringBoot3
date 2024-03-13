package com.springboot.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.product.exception.ProductNotFoundByIdException;
import com.springboot.product.exception.ProductsNotFoundException;
import com.springboot.product.model.Product;
import com.springboot.product.repository.ProductRepository;
import com.springboot.product.service.ProductService;
import com.springboot.product.utility.ResponseStructure;

@Service
public class ProductServiceImpl  implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product p) {
		productRepository.save(p);
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Product Object Created successfully");
		responseStructure.setData(p);
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> findByProductId(int productId) {
		
		Optional<Product> existingProduct = productRepository.findById(productId);
		if(existingProduct.isPresent()) {
			Product product = existingProduct.get();
			ResponseStructure<Product> responseStructure = new ResponseStructure<>();
			responseStructure.setStatuscode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Object Found");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.FOUND);
		}
		else{
			
			throw new ProductNotFoundByIdException("Product Object Not Found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, Product updatedProduct) {
		
		Optional<Product> optional = productRepository.findById(productId);
		if(optional.isPresent()) {
			Product product = optional.get();
			updatedProduct.setProductId(product.getProductId());
			productRepository.save(updatedProduct);
			ResponseStructure<Product> responseStructure = new ResponseStructure<>();
			responseStructure.setStatuscode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Object Updated Successfully");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.FOUND);
		}
		else{
			
			throw new ProductNotFoundByIdException("Product Object Not Found");
		}
			
	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> deleteByProductId(int productId) {
		
		Optional<Product> existingProduct = productRepository.findById(productId);
		if(existingProduct.isPresent()) {
			Product product = existingProduct.get();
			productRepository.deleteById(product.getProductId());
			ResponseStructure<Product> responseStructure = new ResponseStructure<>();
			responseStructure.setStatuscode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Object deleted Successfully");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.FOUND);
		}
		else{
			
			throw new ProductNotFoundByIdException("Product Object Not Found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		
		List<Product> list = productRepository.findAll();
		if(list.isEmpty()) {
			throw new ProductsNotFoundException("Products are not Avaialable!!");
		}
		else {
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatuscode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Objects found Successfully");
			responseStructure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure,HttpStatus.FOUND);
		}
		}
	}
	
