package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tallerdiegogarcia.model.Productsubcategory;
import com.example.tallerdiegogarcia.repositories.ProductCategoryRepository;
import com.example.tallerdiegogarcia.repositories.ProductRepository;
import com.example.tallerdiegogarcia.repositories.ProductSubCategoryRepository;

@Service
public class ProductSubCategoryServiceImp implements ProductSubCategoryService {
	
	private ProductSubCategoryRepository subCategoryRepository;
	private ProductCategoryRepository categoryRepository;
	private ProductRepository productRepository;
	
	
	@Autowired
	public ProductSubCategoryServiceImp(ProductSubCategoryRepository subCategoryRepository,
			ProductCategoryRepository categoryRepository, ProductRepository productRepository) {
		super();
		this.subCategoryRepository = subCategoryRepository;
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Productsubcategory addProductSubCategory(Productsubcategory productsubcategory,Integer categoryID) {
		if(productsubcategory==null) {
			throw new IllegalArgumentException();
		}else if(productsubcategory.getName().length()<5) {
			throw new IllegalArgumentException();
		}else if(categoryRepository.findById(categoryID)==null) {
			throw new IllegalArgumentException();
		}else {
			categoryRepository.findById(categoryID).get().getProductsubcategories().add(productsubcategory);
			return subCategoryRepository.save(productsubcategory);
		}
	}

	@Override
	public Productsubcategory editProductSubCategory(Productsubcategory productsubcategory,Integer categoryID) {
		if(productsubcategory==null) {
			throw new IllegalArgumentException();
		}else if (productsubcategory.getName().length()<5){
			throw new IllegalArgumentException();
		}else if(categoryRepository.findById(categoryID)==null) {
			throw new IllegalArgumentException();
		}else {
			Productsubcategory oldSubcat = subCategoryRepository.findById(productsubcategory.getProductsubcategoryid()).get();
			oldSubcat.setName(productsubcategory.getName());	
			oldSubcat.setModifieddate(productsubcategory.getModifieddate());
			oldSubcat.setRowguid(productsubcategory.getRowguid());
			oldSubcat.setProductcategory(productsubcategory.getProductcategory());
			oldSubcat.setProductsubcategoryid(productsubcategory.getProductsubcategoryid());
			subCategoryRepository.save(oldSubcat);
			return oldSubcat;
		}
	}
	
	public Productsubcategory findSubCategory(Integer id) {
		return subCategoryRepository.findById(id).get();
	}

	@Override
	public Iterable<Productsubcategory> findAll() {
		return subCategoryRepository.findAll();
	}

	@Override
	public Optional<Productsubcategory> findById(Integer id) {
		return subCategoryRepository.findById(id);
	}

	@Override
	public void delete(Productsubcategory productsubcategory) {
		if(productRepository.findByProductsubcategoryProductsubcategoryid(productsubcategory.getProductsubcategoryid()).isEmpty()) {
			subCategoryRepository.delete(productsubcategory);
		}else {
			throw new IllegalArgumentException();
		}
	}

}
