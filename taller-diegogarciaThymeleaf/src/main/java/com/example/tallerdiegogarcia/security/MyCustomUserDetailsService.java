package com.example.tallerdiegogarcia.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.tallerdiegogarcia.model.UserWeb;
import com.example.tallerdiegogarcia.repositories.UserRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	private UserRepository ur;
	
	@Autowired
	public MyCustomUserDetailsService(UserRepository ur) {
		this.ur = ur;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		UserWeb user = ur.findByUsername(username).get();
		
		if (user != null) {
			
			User.UserBuilder builder = User.withUsername(username).password(user.getPassword()).roles(user.getType().toString());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}