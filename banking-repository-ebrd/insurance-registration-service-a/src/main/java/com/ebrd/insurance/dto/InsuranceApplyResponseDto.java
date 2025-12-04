package com.ebrd.insurance.dto;

import java.time.LocalDate;

public record InsuranceApplyResponseDto(
    Long customerId,
    String customerName,
    String phoneNumber,
    String emailId,
    String carNumber,
    String manufacturer,
    String model,
    int makingYear,
    LocalDate insuranceExpiryDate) {}

