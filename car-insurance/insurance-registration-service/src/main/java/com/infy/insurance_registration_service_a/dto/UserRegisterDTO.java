package com.infy.insurance_registration_service_a.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for UserRegister entity
 * Used for transferring customer registration data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    private Long customerId;

    @NotBlank(message = "Customer name is required")
    @Size(max = 255, message = "Customer name cannot exceed 255 characters")
    private String customerName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10,20}$", message = "Phone number must be between 10 and 20 digits")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String emailId;

    @NotBlank(message = "Car number is required")
    @Size(max = 50, message = "Car number cannot exceed 50 characters")
    private String carNumber;

    @NotBlank(message = "Manufacturer is required")
    @Size(max = 100, message = "Manufacturer cannot exceed 100 characters")
    private String manufacturer;

    @NotBlank(message = "Model is required")
    @Size(max = 100, message = "Model cannot exceed 100 characters")
    private String model;

    @NotNull(message = "Making year is required")
    @Min(value = 1900, message = "Making year must be after 1900")
    @Max(value = 2100, message = "Making year must be before 2100")
    private Integer makingYear;

    private LocalDate insuranceExpiryDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
