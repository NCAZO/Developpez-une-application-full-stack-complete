package com.openclassrooms.mddapi.controllers;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.dto.request.LoginRequest;
import com.openclassrooms.mddapi.dto.request.RegisterRequest;
import com.openclassrooms.mddapi.dto.response.MessageResponse;
import com.openclassrooms.mddapi.dto.response.UserInfoResponse;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.JwtService;
import com.openclassrooms.mddapi.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

//	@Autowired
//	private AuthService authService;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        return authService.login(request);
//    }
//    
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//    	return authService.register(request);
//    }

//    @GetMapping("/me")
//    public ResponseEntity<UserDTO> getMe(Authentication authentication) {
//        Integer userId = userService.getUserIdByName(authentication);
//
//        User user = authService.getUserById(userId);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        
//        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//        return ResponseEntity.ok(userDTO);
//    }

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtService jwtService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtService.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail()));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
//	    if (userRepository.existsByUsername(registerRequest.getName())) {
//	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//	    }

		if (userRepository.existsByEmail(registerRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		Long time = Date.from(Instant.now()).getTime();

		// Create new user's account
		User user = new User(registerRequest.getEmail(), registerRequest.getName(),
				encoder.encode(registerRequest.getPassword()));

		user.setCreated_at(new Date(time));
		user.setUpdated_at(new Date(time));

		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/logOut")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtService.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}

	@GetMapping("/me")
	public ResponseEntity<UserDTO> getMe(Authentication authentication) {
//		Integer userId = userService.getUserIdByName(authentication);
//
//		User user = authService.getUserById(userId);
//		if (user == null) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//
//		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		
		return ResponseEntity.ok(new UserDTO());
	}
}
