package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

//	public Long getUserIdByName(Authentication authentication) {
//		User userFind = userRepository.findByName(authentication.getName());
//		if (userFind == null) {
//			return null;
//		}
//		return userFind.getId();
//	}

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User saveUserInfo(User user) throws Exception {
        if (user.getId() == null) {
            throw new Exception("User do not exist !");
        }

        Optional<User> currentUserId = userRepository.findById(user.getId());

        if (currentUserId.isPresent()) {
            currentUserId.get().setEmail(user.getEmail());
            currentUserId.get().setName(user.getName());
            currentUserId.get().setPassword(user.getPassword());
            userRepository.save(currentUserId.get());
        } else {
            throw new Exception("User do not exist !");
        }
        return user;
    }
}