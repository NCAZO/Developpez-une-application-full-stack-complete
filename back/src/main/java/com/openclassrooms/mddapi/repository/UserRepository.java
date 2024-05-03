package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.User;
import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
//	Optional<User> findByName(String name);

//	  Boolean existsByUsername(String username);
	  
	  Optional<User>  findByEmail(String email);

	  Boolean existsByEmail(String email);

}
