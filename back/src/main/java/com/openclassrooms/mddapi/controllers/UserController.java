package com.openclassrooms.mddapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
	    User userFind = userService.getUserById(id);

	    if (userFind != null) {
	        return ResponseEntity.ok(userFind);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
