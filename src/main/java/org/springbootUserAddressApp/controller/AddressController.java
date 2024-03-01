package org.springbootUserAddressApp.controller;

import java.util.List;

import org.springbootUserAddressApp.dto.Address;
import org.springbootUserAddressApp.dto.ResponseStructure;
import org.springbootUserAddressApp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/address")
public class AddressController {
	@Autowired
	private AddressService service;
	
	@PostMapping("/{id}")
	public ResponseEntity<ResponseStructure<Address>> addAddress(@RequestBody Address ad,@PathVariable int id){
		return service.addAddress(ad, id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<List<Address>>> findBYid(@PathVariable int id){
		return service.findBYid(id);
	}
	@PostMapping("/verifyEmail")
	public ResponseEntity<ResponseStructure<List<Address>>> findBYUserEmail(@RequestParam String email,@RequestParam String password){
		return service.findBYUserEmail(email, password);
	}
	
}
