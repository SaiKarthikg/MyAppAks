package com.example.banking.registration.controller;

import com.example.banking.registration.dto.LoginRequest;
import com.example.banking.registration.dto.UserRequest;
import com.example.banking.registration.dto.UserResponse;
import com.example.banking.registration.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "User Controller", description = "Endpoints for user registration, login, and details retrieval")
@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user with the provided details.")
    @PostMapping("/registerUser")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest usersRequest) {
        UserResponse user = usersService.registerUser(usersRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Get user details by ID", description = "Fetches user details for the given user ID.")
    @GetMapping("/getUserdetailsById/{id}")
    public ResponseEntity<UserResponse> getUserDetailsById(@PathVariable("id") Long id) {
        UserResponse user = usersService.getUserDetailsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Login", description = "Authenticates a user and returns user details if successful.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserResponse user = usersService.login(loginRequest);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("status", "fail", "message", "Login Credentials are not correct. Please try with Valid one."));
        }
    }
}
