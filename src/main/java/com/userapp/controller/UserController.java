package com.userapp.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.userapp.model.User;
import com.userapp.model.UserDaoService;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;
	
	//get all users
		@GetMapping("/users")
		public List<User> retrieveAllUsers() {
			return service.findAll(); 
		}
		
		//get one user by id
		@GetMapping("/users/{id}")
		public User retrieveUser(@PathVariable int id) {
			User user = service.findOne(id);
			if(user == null) {
				throw new UserNotFoundException("id-"+ id);
			}
			
			return service.findOne(id); 
			
		}
		
		//register user
		@PostMapping("/register")
		public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {
			User savedUser = service.save(user);
			
			URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getUserId()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		
		//delete a user
		@DeleteMapping("/users/{id}")
		public void deleteby(@PathVariable int id) {
			if(service.findOne(id).getUserId() == null) {
				throw new UserNotFoundException("id-"+ id);
			}
			
			User user = service.deleteById(id);
		
		}
	
	@RequestMapping("/greet/{name}")
	public String welcomeMessage(@PathVariable("name") String name) {
		return "Welcome " + name;
	}
}
