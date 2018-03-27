package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping(value= {"/","/login"})
	public String showData() {
		return "login";
	}
}
