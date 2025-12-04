package com.example.banking.registration.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "User request DTO for registration")
public class UserRequest {

    @NotBlank
    private String userName;

    @NotBlank
    @Email
    private String emailId;

    @NotBlank
    @Size(min = 10, max = 10)
    private String phoneNumber;

    @NotBlank
    private String organizationName;

    @NotBlank
    private String taxId;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String confirmPassword;
}
