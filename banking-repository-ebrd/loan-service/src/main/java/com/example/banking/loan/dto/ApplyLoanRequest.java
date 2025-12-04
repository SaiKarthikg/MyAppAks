package com.example.banking.loan.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ApplyLoanRequest(String loanId,
                               @NotBlank String userId,
                               @NotBlank String userName,
                               @NotBlank String organizationName,
                               @NotBlank String loanStatus,
                               @NotBlank BigDecimal requiredAmount,
                               @NotBlank String loanType,
                               @NotBlank String loanPurpose
){
}
