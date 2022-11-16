package com.example.bookstore.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestApi {
	
	@GetMapping("/admin")
	public String admin() {
		return "Admin";
	}
	
	@GetMapping("/users")
	public String user() {
		return "User";
	}
	
	@GetMapping("/test")
	public String test() {
		return "Test";
	}
	
	@GetMapping("/me")
	public String getMySelf() {
		return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().toString();
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/method-admin")
	public String method_admin() {
		return "admin method";
	}

}
