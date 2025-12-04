package com.infy.insurance_registration_service_a.controller;

import com.infy.insurance_registration_service_a.dto.UserRegisterProductDTO;
import com.infy.insurance_registration_service_a.service.UserRegisterProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for User Register Product operations
 */
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class UserRegisterProductController {

    private final UserRegisterProductService userRegisterProductService;

    /**
     * Create a new user_register_product
     *
     * @param userRegisterProductDTO Product registration data
     * @return Created product registration
     */
    @PostMapping
    public ResponseEntity<UserRegisterProductDTO> createUserRegisterProduct(@Valid @RequestBody UserRegisterProductDTO userRegisterProductDTO) {
        UserRegisterProductDTO createdProduct = userRegisterProductService.registerProduct(userRegisterProductDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
}

