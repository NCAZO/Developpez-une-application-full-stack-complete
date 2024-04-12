package com.openclassrooms.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.request.SignUpRequest;
import com.openclassrooms.mddapi.services.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/auth/register")
	public ResponseEntity<?>register(@RequestBody User user){
		return ResponseEntity.ok(authService.register(user));
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody SignUpRequest request) throws Exception{
		return ResponseEntity.ok(authService.login(request));
	}
}
