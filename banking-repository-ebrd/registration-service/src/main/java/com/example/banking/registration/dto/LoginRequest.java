package com.example.banking.registration.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Login request DTO for user authentication")
public class LoginRequest {
    @NotBlank
    private String emailId;
    @NotBlank
    private String password;
}
