package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public Optional<User> findById(Long id);
	
	public Optional<User> findByEmail(String email);
    
    public User findByUsername(String username);

}
