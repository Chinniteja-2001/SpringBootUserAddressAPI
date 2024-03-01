package org.springbootUserAddressApp.controller;

import org.springbootUserAddressApp.dto.ResponseStructure;
import org.springbootUserAddressApp.dto.User;
import org.springbootUserAddressApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User u) {
		return service.saveUser(u);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User u) {
		return service.updateUser(u);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable(name="id")int id) {
		return service.findUserById(id);
	}
	@PostMapping("/verifyByEmail")
	public ResponseEntity<ResponseStructure<User>> verifyEmail(@RequestParam(name="email")String email,@RequestParam(name="password")String password) {
		return service.verifyByEmail(email, password);
	}
	@PostMapping("/verifyByPhone")
	public ResponseEntity<ResponseStructure<User>> verifyPhone(@RequestParam(name="phone")long phone,@RequestParam(name="password")String password) {
		return service.verifyByPhone(phone, password);
	}
}
