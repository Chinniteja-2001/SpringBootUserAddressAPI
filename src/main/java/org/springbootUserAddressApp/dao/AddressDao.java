package org.springbootUserAddressApp.dao;

import java.util.List;
import java.util.Optional;

import org.springbootUserAddressApp.dto.Address;
import org.springbootUserAddressApp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepository repository;
	
	public Address addAddress(Address ad){
		return repository.save(ad);
	}
	
	public Optional<List<Address>>findAddressBYUserId(int user_id){
		return repository.findAddressByUserId(user_id);
	}
	
	public Optional<List<Address>> findAddressByUserEmail(String email,String password){
		return repository.findAddressByUserEmail(email, password);
	}
}
