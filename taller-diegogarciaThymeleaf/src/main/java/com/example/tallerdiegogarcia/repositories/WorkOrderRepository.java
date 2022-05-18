package com.example.tallerdiegogarcia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tallerdiegogarcia.model.Workorder;

@Repository
public interface WorkOrderRepository extends CrudRepository<Workorder, Integer>{

}
