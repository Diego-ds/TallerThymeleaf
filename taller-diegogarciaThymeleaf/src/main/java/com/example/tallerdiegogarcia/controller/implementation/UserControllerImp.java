package com.example.tallerdiegogarcia.controller.implementation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.tallerdiegogarcia.controller.interfaces.UserController;

@Controller
public class UserControllerImp implements UserController {
	
	@GetMapping("/login")
	public String login(Model model) {
		return "/login";
	}
	
}
