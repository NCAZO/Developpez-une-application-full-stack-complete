package com.openclassrooms.mddapi.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public User getUserByName(String name) {
//        return userRepository.findByName(name);
//    }

//    public Integer getUserIdByName(Authentication authentication) {
//        User userFind = userRepository.findByName(authentication.getName());
//        if (userFind == null) {
//            return null;
//        }
//        return userFind.getId();
//    }

//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean checkPassword(User user, String password) {
		return user.getPassword().equals(password);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email) != null;
	}
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}
}