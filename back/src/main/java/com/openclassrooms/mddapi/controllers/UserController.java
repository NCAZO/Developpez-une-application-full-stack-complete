package com.openclassrooms.mddapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.UserService;


import java.util.Objects;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	User getUser(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
	}
	
    ResponseEntity<User> updateUser(@RequestBody @Valid User user) {
        User updateUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> byEmail = userService.findUserByEmail(updateUser.getEmail());
        if (byEmail.isPresent() && !Objects.equals(updateUser.getEmail(), user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        updateUser = userService.updateUser(updateUser);
        return ResponseEntity.ok(updateUser);
    }
	
}
