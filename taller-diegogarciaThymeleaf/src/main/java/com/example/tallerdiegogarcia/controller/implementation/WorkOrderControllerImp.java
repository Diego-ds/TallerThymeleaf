package com.example.tallerdiegogarcia.controller.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tallerdiegogarcia.controller.interfaces.WorkOrderController;
import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.model.Workorder;
import com.example.tallerdiegogarcia.services.ProductService;
import com.example.tallerdiegogarcia.services.WorkOrderService;
import com.example.tallerdiegogarcia.validate.WorkOrderValidation;

@Controller
public class WorkOrderControllerImp implements WorkOrderController {

	ProductService productService;
	WorkOrderService orderService;
	
	@Autowired
	public WorkOrderControllerImp(ProductService productService, WorkOrderService orderService) {
		super();
		this.productService = productService;
		this.orderService = orderService;
	}

	@GetMapping("/workorders/add")
	public String addWorkorder(Model model) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("workorder", new Workorder());
		return "workorders/add-workorder";
	}

	@GetMapping("/workorders/del/{id}")
	public String deleteWorkorder(@PathVariable("id") Integer id, Model model) {
		Workorder workorder = orderService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid workorder Id:" + id));
		orderService.delete(workorder);
		model.addAttribute("workorders", orderService.findAll());
		return "workorders/index";
	}
	
	@GetMapping("/workorders/associated/{id}")
	public String associatedProducts(@PathVariable("id") Integer id, Model model) {
		Product prod = productService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
		
		model.addAttribute("workorders", prod.getWorkorders());
		return "workorders/index";
	}

	@GetMapping("/workorders/")
	public String indexWorkorders(Model model) {
		model.addAttribute("workorders", orderService.findAll());
		return "workorders/index";
	}

	@PostMapping("/workorders/add")
	public String saveWorkorder( @Validated(WorkOrderValidation.class) @ModelAttribute Workorder workorder, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(workorder.getStartdate().isAfter(workorder.getEnddate())) {
				bindingResult.addError(new FieldError("workorder","startdate","La fecha de inicio debe ser menor a la fecha final"));
			}
			if (bindingResult.hasErrors()) {
				 model.addAttribute("products", productService.findAll());
				 return "/workorders/add-workorder";
			}
			orderService.addWorkOrder(workorder, workorder.getProduct().getProductid());
		}
		return "redirect:/workorders/";		
	}

	@GetMapping("/workorders/edit/{id}")
	public String showUpdateWorkorder(@PathVariable("id") Integer id, Model model) {
		Optional<Workorder> workorder = orderService.findById(id);
		if (workorder == null)
			throw new IllegalArgumentException("Invalid workorder Id:" + id);
		
		model.addAttribute("workorder", workorder.get());
		model.addAttribute("products", productService.findAll());
		return "workorders/update-workorder";
	}

	@PostMapping("/workorders/edit/{id}")
	public String updateWorkorder(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			@Validated(WorkOrderValidation.class) @ModelAttribute Workorder workorder,BindingResult bindingResult , Model model) {
		if (action != null && !action.equals("Cancel")) {
			if(workorder.getStartdate().isAfter(workorder.getEnddate())) {
				bindingResult.addError(new FieldError("workorder","startdate","La fecha de inicio debe ser menor a la fecha final"));
			}
			 if (bindingResult.hasErrors()) {
				model.addAttribute("workorder", workorder);
				workorder.setWorkorderid(id);
				model.addAttribute("products", productService.findAll());
				return "workorders/update-workorder";
			 }else {
				workorder.setWorkorderid(id);
				orderService.editWorkOrder(workorder,workorder.getProduct().getProductid());
			 }
			model.addAttribute("workorders", orderService.findAll());	
		}
		
		return "redirect:/workorders/";
	}
}
