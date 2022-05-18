package com.example.tallerdiegogarcia.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.tallerdiegogarcia.model.UserWeb;

public interface UserRepository extends CrudRepository<UserWeb,Integer>{
	
	public Optional<UserWeb> findByUsername(String username);
}
