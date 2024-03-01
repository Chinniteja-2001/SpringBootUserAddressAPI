package org.springbootUserAddressApp.service;

import java.util.Optional;

import org.springbootUserAddressApp.dao.UserDao;
import org.springbootUserAddressApp.dto.ResponseStructure;
import org.springbootUserAddressApp.dto.User;
import org.springbootUserAddressApp.exception.IdNotFoundException;
import org.springbootUserAddressApp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User u){
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(dao.saveUser(u));
		structure.setMessage("user saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User u){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> recUser=dao.findUserById(u.getId());
		if(recUser.isPresent()) {
			User user=recUser.get();
			user.setAge(u.getAge());
			user.setEmail(u.getEmail());
			user.setGender(u.getGender());
			user.setName(u.getName());
			user.setPhone(u.getPhone());
			user.setPassword(u.getPassword());
			structure.setData(u);
			structure.setMessage("user updated");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			
		}
		throw new IdNotFoundException("required user is not present");
	}
	
	public ResponseEntity<ResponseStructure<User>> findUserById(int id){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> recUser=dao.findUserById(id);
		if(recUser.isPresent()) {
			
			structure.setData(recUser.get());
			structure.setMessage("user present");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			
		}
		throw new IdNotFoundException("user id is not present");
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyByEmail(String email,String password){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> recUser=dao.verifyByEmail(email, password);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("user is verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("either email or password or both are incorrect ");
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyByPhone(long phone,String password){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> recUser=dao.verifyByPhone(phone, password);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("user is verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("either phone number or password or both are incorrect ");
	}
	
	
	
}
