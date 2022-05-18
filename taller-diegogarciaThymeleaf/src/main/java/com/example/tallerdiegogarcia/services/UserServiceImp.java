package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tallerdiegogarcia.model.UserWeb;
import com.example.tallerdiegogarcia.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	UserRepository repository;

	@Autowired
	public UserServiceImp(UserRepository repository) {
		this.repository = repository;
	}
	
	public UserWeb addUser(UserWeb user) {
		return repository.save(user);
	}
	
	public Optional<UserWeb> findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	public Optional<UserWeb> findById(Integer id){
		return repository.findById(id);
	}
	
}
