package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tallerdiegogarcia.model.Workorder;
import com.example.tallerdiegogarcia.repositories.ProductRepository;
import com.example.tallerdiegogarcia.repositories.WorkOrderRepository;

@Service
public class WorkOrderServiceImp implements WorkOrderService {
	
	private ProductRepository productRepository;
	private WorkOrderRepository orderRepository;
	
	public WorkOrderServiceImp(WorkOrderRepository orderRepository, ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.orderRepository=orderRepository;
	}

	@Override
	public Workorder addWorkOrder(Workorder workorder,Integer productID) {
		if(workorder == null) {
			throw new IllegalArgumentException();
		}else if(workorder.getOrderqty()<0 || workorder.getScrappedqty()<0) {
			throw new IllegalArgumentException();
		}else if(workorder.getStartdate().isAfter(workorder.getEnddate())) {
			throw new IllegalArgumentException();
		}else if(productRepository.findById(productID).isEmpty()) {
			throw new IllegalArgumentException();
		}else {
			productRepository.findById(productID).get().getWorkorders().add(workorder);
			return orderRepository.save(workorder);
		}
	}

	@Override
	public Workorder editWorkOrder(Workorder workorder,Integer productID) {
		if(workorder == null) {
			throw new IllegalArgumentException();
		}else if(workorder.getOrderqty()<0 || workorder.getScrappedqty()<0) {
			throw new IllegalArgumentException();
		}else if(workorder.getStartdate().isAfter(workorder.getEnddate())) {
			throw new IllegalArgumentException();
		}else if(productRepository.findById(productID).isEmpty()) {
			throw new IllegalArgumentException();
		}else if(orderRepository.findById(workorder.getWorkorderid()).isEmpty()){
			throw new IllegalArgumentException();
		}else {
			Workorder oldOrder = orderRepository.findById(workorder.getWorkorderid()).get();
			oldOrder.setOrderqty(workorder.getOrderqty());
			oldOrder.setScrappedqty(workorder.getScrappedqty());
			oldOrder.setStartdate(workorder.getStartdate());
			oldOrder.setEnddate(workorder.getEnddate());
			oldOrder.setModifieddate(workorder.getModifieddate());
			oldOrder.setProduct(workorder.getProduct());
			oldOrder.setDuedate(workorder.getDuedate());
			oldOrder.setScrapreason(workorder.getScrapreason());
			oldOrder.setWorkorderid(workorder.getWorkorderid());
			orderRepository.save(oldOrder);
			return oldOrder;
		}
	}

	@Override
	public Optional<Workorder> findById(Integer id) {
		return orderRepository.findById(id);
	}

	@Override
	public Iterable<Workorder> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public void delete(Workorder workorder) {
		orderRepository.delete(workorder);
	}

}
