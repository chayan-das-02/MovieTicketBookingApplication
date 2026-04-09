package com.movieticket.controller;

import com.movieticket.dto.UserDTO;
import com.movieticket.dto.UserRegisterRequest;
import com.movieticket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        UserDTO userDTO = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId, 
                                          @RequestHeader(value = "X-User-Id", required = false) String xUserId,
                                          @RequestHeader(value = "X-User-Role", required = false) String xUserRole) {
        // Allow users to view their own profile or admins to view any profile
        // Client sends X-User-Id and X-User-Role headers with localStorage data
        if (xUserRole != null && "ADMIN".equals(xUserRole)) {
            // Admin can view any user
            UserDTO userDTO = userService.getUserById(userId);
            return ResponseEntity.ok(userDTO);
        } else if (xUserId != null && xUserId.equals(userId.toString())) {
            // User can view their own profile
            UserDTO userDTO = userService.getUserById(userId);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(403).body(new ErrorResponse(
                "Access Denied",
                "You can only view your own profile"
            ));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestHeader(value = "X-User-Role", required = false) String xUserRole,
                                         @RequestHeader(value = "x-user-role", required = false) String xUserRoleLower) {
        // Check both uppercase and lowercase variants
        String role = xUserRole != null ? xUserRole : xUserRoleLower;
        
        System.out.println("[DEBUG] GET /users - X-User-Role: " + xUserRole + ", x-user-role: " + xUserRoleLower + ", final role: " + role);
        
        // Check if user is admin
        if (!"ADMIN".equals(role)) {
            System.out.println("[DEBUG] Access denied - role is: " + role);
            return ResponseEntity.status(403).body(new ErrorResponse(
                "Access Denied",
                "Only administrators can view all users. Role: " + role
            ));
        }
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, 
                                        @Valid @RequestBody UserRegisterRequest request,
                                        @RequestHeader(value = "X-User-Id", required = false) String xUserId,
                                        @RequestHeader(value = "X-User-Role", required = false) String xUserRole) {
        // Allow users to update their own profile or admins to update any profile
        if (xUserRole != null && "ADMIN".equals(xUserRole)) {
            // Admin can update any user
            UserDTO userDTO = userService.updateUser(userId, request);
            return ResponseEntity.ok(userDTO);
        } else if (xUserId != null && xUserId.equals(userId.toString())) {
            // User can update their own profile
            UserDTO userDTO = userService.updateUser(userId, request);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(403).body(new ErrorResponse(
                "Access Denied",
                "You can only update your own profile"
            ));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId,
                                        @RequestHeader(value = "X-User-Role", required = false) String xUserRole) {
        // Only admins can delete users
        if (xUserRole == null || !"ADMIN".equals(xUserRole)) {
            return ResponseEntity.status(403).body(new ErrorResponse(
                "Access Denied",
                "Only administrators can delete users"
            ));
        }
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    private String determineUserRole(String auth) {
        // Placeholder for role determination from auth header
        return null;
    }

    // Simple error response class
    static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() { return error; }
        public String getMessage() { return message; }
    }
}
