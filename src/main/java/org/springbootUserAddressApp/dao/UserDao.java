package org.springbootUserAddressApp.dao;

import java.util.Optional;

import org.springbootUserAddressApp.dto.User;
import org.springbootUserAddressApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User u) {
		return repository.save(u);
	}
	
	public Optional<User> findUserById(int id){
		return repository.findById(id);
	}
	
	public Optional<User> verifyByPhone(long phone,String password){
		return repository.verifyByPhone(phone, password);
	}
	
	public Optional<User> verifyByEmail(String email,String password){
		return repository.verifyByEmail(email, password);
	}
	
}
