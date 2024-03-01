package org.springbootUserAddressApp.service;

import java.util.List;
import java.util.Optional;

import org.springbootUserAddressApp.dao.AddressDao;
import org.springbootUserAddressApp.dao.UserDao;
import org.springbootUserAddressApp.dto.Address;
import org.springbootUserAddressApp.dto.ResponseStructure;
import org.springbootUserAddressApp.dto.User;
import org.springbootUserAddressApp.exception.IdNotFoundException;
import org.springbootUserAddressApp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	@Autowired
	private AddressDao dao;
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Address>> addAddress(Address ad,int id){
		ResponseStructure<Address> structure=new ResponseStructure<>();
		Optional<User> recUser=userDao.findUserById(id);
		if(recUser.isPresent()) {
			User u=recUser.get();
			u.getAddresses().add(ad);
			ad.setUser(u);
			structure.setData(dao.addAddress(ad));
			structure.setMessage("address added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException("user not found");
	}
	
	public ResponseEntity<ResponseStructure<List<Address>>> findBYid(int id){
		ResponseStructure<List<Address>> structure=new ResponseStructure<>();
		Optional<List<Address>> recAddress=dao.findAddressBYUserId(id);
		if(recAddress.isPresent()) {
			
			structure.setData(recAddress.get());
			structure.setMessage("address found");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<List<Address>>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException("user not found");
	}
	
	public ResponseEntity<ResponseStructure<List<Address>>> findBYUserEmail(String email,String password){
		ResponseStructure<List<Address>> structure=new ResponseStructure<>();
		Optional<List<Address>> recAddress=dao.findAddressByUserEmail(email, password);
		if(recAddress.isPresent()) {
			
			structure.setData(recAddress.get());
			structure.setMessage("address found");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<List<Address>>>(structure, HttpStatus.CREATED);
		}
		throw new InvalidCredentialsException("either email or password or both are wrong");
	}
}
