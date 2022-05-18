package com.example.tallerdiegogarcia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tallerdiegogarcia.model.Scrapreason;

@Repository
public interface ScrapReasonRepository extends CrudRepository<Scrapreason, Integer> {

}
