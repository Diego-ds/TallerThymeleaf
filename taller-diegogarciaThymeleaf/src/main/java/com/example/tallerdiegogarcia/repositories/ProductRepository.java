package com.example.tallerdiegogarcia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tallerdiegogarcia.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	public List<Product> findByProductsubcategoryProductsubcategoryid(Integer id);
}
