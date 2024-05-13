package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.request.LoginRequest;
import com.openclassrooms.mddapi.dto.request.RegisterRequest;
import com.openclassrooms.mddapi.dto.response.AuthResponse;
import com.openclassrooms.mddapi.dto.response.MessageResponse;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.AuthService;
import com.openclassrooms.mddapi.services.JwtService;
import com.openclassrooms.mddapi.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtService jwtService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){
		return authService.register(registerRequest);
	}

	@PostMapping("/logOut")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtService.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}

	@GetMapping("/me")
	public User getMe() {
		SecurityContext securityContext = SecurityContextHolder.getContext();

		Authentication authentication = securityContext.getAuthentication();

		String username = authentication.getName();

		Optional<User> _user = userService.findUserByName(username);

		_user.get().setPassword(null);

		return _user.get();
	}

//	@GetMapping("/me")
//	public ResponseEntity<User> getMe(Authentication authentication) {
//		Long userId = userService.getUserIdByName(authentication);
//
//		User user = authService.getUserById(userId);
//		if (user == null) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//
//		User userDTO = modelMapper.map(user, User.class);
//		return ResponseEntity.ok(userDTO);
//	}
}
