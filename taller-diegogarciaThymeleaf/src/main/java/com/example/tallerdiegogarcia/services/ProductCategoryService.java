package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Productcategory;

public interface ProductCategoryService {
	public Productcategory addProductCategory(Productcategory productcategory);
	public Productcategory editProductCategory(Productcategory productcategory);
	public Productcategory findCategory(Integer id);
	public Iterable<Productcategory> findAll();
	public Optional<Productcategory> findById(Integer id);
	public void delete(Productcategory productcategory);
}
