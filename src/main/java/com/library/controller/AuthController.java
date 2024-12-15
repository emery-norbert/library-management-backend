package com.library.controller;

import com.library.model.User;
import com.library.service.AuthService;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean isAuthenticated = authService.authenticate(user.getUsername(), user.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    // Register Endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Check if the username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(400).body("Username already exists");
        }

        // Save the new user
        userRepository.save(user);
        return ResponseEntity.status(201).body("User registered successfully");
    }

    // Delete User Endpoint
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // Check if the user exists
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user); // Delete the user
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();  // Retrieve all users from the database
        return ResponseEntity.ok(users);
    }
}
