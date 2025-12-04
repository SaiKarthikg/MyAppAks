package com.example.banking.registration.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Public-safe user response (no sensitive fields).
 */
@Schema(description = "User response DTO for API responses")
public record UserResponse(
    @Schema(description = "User ID") String userId,
    @Schema(description = "User name") String userName,
    @Schema(description = "Email ID") String emailId,
    @Schema(description = "Phone number") String phoneNumber,
    @Schema(description = "Organization name") String organizationName,
    @Schema(description = "Tax ID/GST ID") String taxIdGstId
) {}
