package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.model.Productsubcategory;
import com.example.tallerdiegogarcia.repositories.ProductRepository;
import com.example.tallerdiegogarcia.repositories.ProductSubCategoryRepository;

@Service
public class ProductServiceImp implements ProductService {
	
	private ProductSubCategoryRepository subCategoryRepository;
	private ProductRepository productRepository;
	

	public ProductServiceImp(ProductSubCategoryRepository subCategoryRepository, ProductRepository productRepository) {
		this.subCategoryRepository = subCategoryRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Product addProduct(Product product,Integer subCategoryID) {
		Optional<Productsubcategory> subcategory = subCategoryRepository.findById(subCategoryID);
		if(product == null) {
			throw new IllegalArgumentException();
		}else if(product.getProductnumber() == null) {
			throw new IllegalArgumentException();
		}else if(product.getSellstartdate().isAfter(product.getSellenddate())) {
			throw new IllegalArgumentException();
		}else if(product.getSize()<0 || product.getWeight()<0) {
			throw new IllegalArgumentException();
		}else if(subcategory.isEmpty()) {
			throw new IllegalArgumentException();
		}else {
			subcategory.get().getProducts().add(product);
			return productRepository.save(product);
		}
	}

	@Override
	public Product editProduct(Product product,Integer subCategoryID) {
		Optional<Productsubcategory> subcategory = subCategoryRepository.findById(subCategoryID);
		if(product == null) {
			throw new IllegalArgumentException();
		}else if(product.getProductnumber() == null) {
			throw new IllegalArgumentException();
		}else if(product.getSellstartdate().isAfter(product.getSellenddate())) {
			throw new IllegalArgumentException();
		}else if(product.getSize()<0 || product.getWeight()<0) {
			throw new IllegalArgumentException();
		}else if(subcategory.isEmpty()) {
			throw new IllegalArgumentException();
		}else {
			Product oldProduct = productRepository.findById(product.getProductid()).get();
			if(oldProduct==null) {
				throw new IllegalArgumentException();
			}else {
				oldProduct.setName(product.getName());
				oldProduct.setProductnumber(product.getProductnumber());
				oldProduct.setSize(product.getSize());
				oldProduct.setWeight(product.getWeight());
				oldProduct.setSellstartdate(product.getSellstartdate());
				oldProduct.setSellenddate(product.getSellenddate());
				oldProduct.setProductid(product.getProductid());
				return productRepository.save(oldProduct);			
			}
		}			
	}

	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}



}
