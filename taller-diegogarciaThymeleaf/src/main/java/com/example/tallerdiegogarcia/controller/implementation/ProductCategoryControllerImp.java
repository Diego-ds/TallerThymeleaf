package com.example.tallerdiegogarcia.controller.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tallerdiegogarcia.controller.interfaces.ProductCategoryController;
import com.example.tallerdiegogarcia.model.Productcategory;
import com.example.tallerdiegogarcia.services.ProductCategoryService;
import com.example.tallerdiegogarcia.validate.CategoryValidation;

@Controller
public class ProductCategoryControllerImp implements ProductCategoryController {
	
	ProductCategoryService service;
	
	@Autowired
	public ProductCategoryControllerImp(ProductCategoryService service) {
		this.service = service;
	}

	@GetMapping("/categories/add")
	public String addCategory(Model model) {
		model.addAttribute("productcategory", new Productcategory());
		return "categories/add-category";
	}

	@GetMapping("/categories/del/{id}")
	public String deleteCategory(@PathVariable("id") Integer id, Model model) {
		Productcategory category = service.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
		try {
			service.delete(category);
		}catch(IllegalArgumentException e) {
			model.addAttribute("cannotdelete", true);
		}
		model.addAttribute("categories", service.findAll());
		return "categories/index";
	}

	@GetMapping("/categories/")
	public String indexProducts(Model model) {
		model.addAttribute("categories", service.findAll());
		return "categories/index";
	}

	@PostMapping("/categories/add")
	public String saveCategory( @Validated(CategoryValidation.class) @ModelAttribute Productcategory productcategory, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "/categories/add-category";
			}
			 service.addProductCategory(productcategory);
		}
		return "redirect:/categories/";	
	}
	
	@GetMapping("/categories/show/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		Productcategory category = service.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
		List<Productcategory> cats = new ArrayList<Productcategory>();
		cats.add(category);
		model.addAttribute("categories",cats);
		return "categories/index";
	}

	@GetMapping("/categories/edit/{id}")
	public String showUpdateCategory(@PathVariable("id") Integer id, Model model) {
		Optional<Productcategory> category = service.findById(id);
		if (category == null)
			throw new IllegalArgumentException("Invalid product Id:" + id);
		model.addAttribute("category", category.get());
		return "categories/update-category";
	}

	@PostMapping("/categories/edit/{id}")
	public String updateCategory(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			@Validated(CategoryValidation.class) @ModelAttribute Productcategory category,BindingResult bindingResult , Model model) {
		if (action != null && !action.equals("Cancel")) {
			 if (bindingResult.hasErrors()) {
				model.addAttribute("category", category);
				category.setProductcategoryid(id);
				return "categories/update-category";
			 }else {
				category.setProductcategoryid(id);
				service.editProductCategory(category);
			 }
			model.addAttribute("categories", service.findAll());	
		}
		
		return "redirect:/categories/";
	}
}
