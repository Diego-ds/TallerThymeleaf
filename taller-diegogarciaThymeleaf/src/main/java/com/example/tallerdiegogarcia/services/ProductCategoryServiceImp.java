package com.example.tallerdiegogarcia.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tallerdiegogarcia.model.Productcategory;
import com.example.tallerdiegogarcia.repositories.ProductCategoryRepository;
import com.example.tallerdiegogarcia.repositories.ProductSubCategoryRepository;

@Service
public class ProductCategoryServiceImp implements ProductCategoryService {
	
	
	ProductCategoryRepository repository;
	ProductSubCategoryRepository subRepository;
	
	
	@Autowired
	public ProductCategoryServiceImp(ProductCategoryRepository repository, ProductSubCategoryRepository subRepository) {
		super();
		this.repository = repository;
		this.subRepository = subRepository;
	}

	@Override
	public Productcategory addProductCategory(Productcategory productcategory) {
		if(productcategory !=null) {
			if(productcategory.getName().length() < 3) {
				throw new IllegalArgumentException();
			}else {
				return repository.save(productcategory);
			}
		}else {
			throw new IllegalArgumentException();
		}	
	}

	@Override
	public Productcategory editProductCategory(Productcategory productcategory) {
		if(productcategory != null) {
			Productcategory oldCategory = repository.findById(productcategory.getProductcategoryid()).get();
			if(oldCategory!=null) {
				if(productcategory.getName().length() < 3) {
					throw new IllegalArgumentException();
				}else {
					oldCategory.setName(productcategory.getName());
					oldCategory.setModifieddate(productcategory.getModifieddate());
					oldCategory.setRowguid(productcategory.getRowguid());
					oldCategory.setProductcategoryid(productcategory.getProductcategoryid());
					repository.save(oldCategory);
					return oldCategory;
				}
			}else {
				throw new IllegalArgumentException();
			}
		}else {
			throw new IllegalArgumentException();
		}	
	}
	
	@Override
	public Productcategory findCategory(Integer id) {
		/*Iterator <Productcategory> it = repository.findAll().iterator();
		while(it.hasNext()) {
			Productcategory pt = it.next();
			System.out.println(pt.getName() + " "+pt.getProductcategoryid());			
		}*/
		return repository.findById(id).get();
	}

	@Override
	public Iterable<Productcategory> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Productcategory> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void delete(Productcategory productcategory) {
		if(subRepository.findByProductcategoryProductcategoryid(productcategory.getProductcategoryid()).isEmpty()) {
			repository.delete(productcategory);
		}else {
			throw new IllegalArgumentException();
		}
	}
	

}
