package com.example.banking.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Request body to register a user.
 */
public record RegisterRequest(
    @NotBlank String userName,
    @NotBlank String organizationName,
    @NotBlank @Email String emailId,
    @NotBlank String password,
    @NotBlank String confirmPassword
) {}
