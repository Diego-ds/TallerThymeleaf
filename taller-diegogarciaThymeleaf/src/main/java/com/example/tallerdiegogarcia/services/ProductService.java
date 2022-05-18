package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Product;

public interface ProductService {
	public Product addProduct(Product product,Integer subCategoryID);
	public Product editProduct(Product product,Integer subCategoryID);
	public Iterable<Product> findAll();
	public Optional<Product> findById(Integer id);
	public void delete(Product product);
}
