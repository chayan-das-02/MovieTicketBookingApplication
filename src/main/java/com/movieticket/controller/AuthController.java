package com.movieticket.controller;

import com.movieticket.dto.LoginRequest;
import com.movieticket.dto.LoginResponse;
import com.movieticket.dto.RegisterRequest;
import com.movieticket.entity.User;
import com.movieticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Register a new user
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            // Check if email already exists
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new LoginResponse(null, null, null, null, null, "Email already registered"));
            }

            // Create new user
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword()); // In production, use BCrypt
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setRole(User.UserRole.USER);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            User savedUser = userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new LoginResponse(
                    savedUser.getUserId(),
                    savedUser.getEmail(),
                    savedUser.getFirstName(),
                    savedUser.getLastName(),
                    savedUser.getRole().toString(),
                    "Registration successful"
                ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new LoginResponse(null, null, null, null, null, "Registration failed: " + e.getMessage()));
        }
    }

    /**
     * Login user
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, null, null, null, null, "Invalid email or password"));
            }

            User user = userOptional.get();

            // Check password (in production, use BCrypt.checkpw)
            if (!user.getPassword().equals(request.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, null, null, null, null, "Invalid email or password"));
            }

            return ResponseEntity.ok(new LoginResponse(
                user.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().toString(),
                "Login successful"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new LoginResponse(null, null, null, null, null, "Login failed: " + e.getMessage()));
        }
    }

    /**
     * Check if user is admin
     */
    @GetMapping("/check-admin/{userId}")
    public ResponseEntity<?> checkAdmin(@PathVariable Long userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new LoginResponse(null, null, null, null, null, "User not found"));
            }

            User user = userOptional.get();
            boolean isAdmin = user.getRole() == User.UserRole.ADMIN;

            return ResponseEntity.ok()
                .body(new LoginResponse(
                    user.getUserId(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole().toString(),
                    isAdmin ? "Admin user" : "Regular user"
                ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new LoginResponse(null, null, null, null, null, "Error: " + e.getMessage()));
        }
    }
}
