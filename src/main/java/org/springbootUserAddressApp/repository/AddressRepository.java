package org.springbootUserAddressApp.repository;

import java.util.List;
import java.util.Optional;

import org.springbootUserAddressApp.dto.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	@Query("select u.addresses from User u where u.id=?1")
	public Optional<List<Address>> findAddressByUserId(int user_id);
	
	@Query("select u.addresses from User u where u.email=?1 and u.password=?2")
	public Optional<List<Address>> findAddressByUserEmail(String email,String password);

}
