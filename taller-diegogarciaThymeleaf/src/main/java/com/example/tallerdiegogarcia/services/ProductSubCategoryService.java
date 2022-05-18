package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Productsubcategory;

public interface ProductSubCategoryService {
	public Productsubcategory addProductSubCategory(Productsubcategory productsubcategory, Integer categoryID);
	public Productsubcategory editProductSubCategory(Productsubcategory productsubcategory,Integer categoryID);
	public Iterable<Productsubcategory> findAll();
	public Optional<Productsubcategory> findById(Integer id);
	public void delete(Productsubcategory productsubcategory);
}
