package com.openclassrooms.mddapi.services;


import java.time.Instant;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openclassrooms.mddapi.dto.request.LoginRequest;
import com.openclassrooms.mddapi.dto.request.RegisterRequest;
import com.openclassrooms.mddapi.dto.response.JwtResponse;
import com.openclassrooms.mddapi.dto.response.MessageResponse;
import com.openclassrooms.mddapi.models.User;

import jakarta.validation.Valid;

@Service
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
//    private final JwtUtils jwtUtils;
	private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
//            JwtUtils jwtUtils,
            JwtService jwtService,
            UserService userService) {
        this.authenticationManager = authenticationManager;
//        this.jwtUtils = jwtUtils;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
//        if (userService.existsByEmail(request.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already taken!"));
//        }
//
//	    Long time = Date.from(Instant.now()).getTime();
//        
//        // Create new user's account
//        User newUser = new User();
//        newUser.setId(null);
//        newUser.setName(request.getName());
//		newUser.setEmail(request.getEmail());
//		newUser.setPassword(passwordEncoder.encode(request.getPassword()));
//		
//		newUser.setCreated_at(new Date(time));
//	    newUser.setUpdated_at(new Date(time));
//        
//        userService.createUser(newUser);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }

//	public ResponseEntity<AuthResponse> register(RegisterRequest request) {
//		
//		try {
//			boolean userExists = userService.isUserExistByEmail(request.getEmail());
//	        if (userExists) {
//	        	return new ResponseEntity<AuthResponse>(HttpStatus.CONFLICT);
//	        }
//				
//			User newUser = new User();
//			newUser.setName(request.getName());
//			newUser.setEmail(request.getEmail());
//			newUser.setPassword(passwordEncoder.encode(request.getPassword()));
//	        
//	        userService.createUser(newUser);
//	        
//	        //Générer token ??
//	       return jwtService.generateToken(newUser);
//	        //return token ?
//		}
//		catch (Exception e) {
//			return new ResponseEntity<AuthResponse>(HttpStatus.BAD_REQUEST);
//		}
//    }

//    public ResponseEntity<AuthResponse> login(LoginRequest request) {
//    	
//    	User newUser = new User();
//		newUser.setEmail(request.getEmail());
//		newUser.setPassword(passwordEncoder.encode(request.getPassword()));
//    
//        User user = userService.getUserByEmail(newUser.getEmail());
////        if (user == null) {
////        	return new ResponseEntity<AuthResponse>(HttpStatus.NOT_FOUND);
////        }
//
//        if (user == null || !userService.checkPassword(user ,newUser.getPassword())) {
//            return new ResponseEntity<AuthResponse>(HttpStatus.NOT_FOUND);
//        }
//    
////        ResponseEntity<AuthResponse> tokenObject = jwtService.generateToken(user);
////        return ResponseEntity.ok(tokenObject);
//		
//
//	       return jwtService.generateToken(newUser);
//    }
    
//    @PostMapping("/login")
//    public ResponseEntity<?>login(@Valid @RequestBody LoginRequest request) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(),
//                		request.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtService.generateToken(authentication);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
////        boolean isAdmin = false;
////        User user = this.userService.findByEmail(userDetails.getUsername()).orElse(null);
////        if (user != null) {
////            isAdmin = user.isAdmin();
////        }
//
//        return ResponseEntity.ok(new JwtResponse(
//        		jwt,
//                userDetails.getId(),
//                userDetails.getUsername()));
//    }

    public User getMe(String name) {
		return null;
//        return userRepository.findByName(name);
    }

    public User getUserById(Integer id) {
		return null;
//        if(!userRepository.findById(id).isPresent()) {
//            throw new RuntimeException("User not found");
//        }
//        return userRepository.findById(id).get();
    }
}