package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.JwtService;
import com.openclassrooms.mddapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
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

    @PostMapping("/saveUserInfo")
    public ResponseEntity<User> saveUserInfo(@Valid @RequestBody User user) throws Exception {
        try {
            return ResponseEntity.ok(userService.saveUserInfo(user));
        } catch (Exception e) {
            return (ResponseEntity<User>) ResponseEntity.notFound();
        }
    }
}