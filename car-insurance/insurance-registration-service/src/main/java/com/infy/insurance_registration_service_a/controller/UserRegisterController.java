package com.infy.insurance_registration_service_a.controller;

import com.infy.insurance_registration_service_a.dto.UserRegisterDTO;
import com.infy.insurance_registration_service_a.service.UserRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for User Registration operations
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    /**
     * Create a new user registration
     *
     * @param userRegisterDTO User registration data
     * @return Created user registration
     */
    @PostMapping
    public ResponseEntity<UserRegisterDTO> createUserRegister(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        UserRegisterDTO createdUser = userRegisterService.createUserRegister(userRegisterDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}

