package com.example.tallerdiegogarcia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tallerdiegogarcia.model.Productsubcategory;

@Repository
public interface ProductSubCategoryRepository extends CrudRepository<Productsubcategory, Integer> {
	public List<Productsubcategory> findByProductcategoryProductcategoryid(Integer id);
}
