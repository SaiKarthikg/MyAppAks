package com.example.banking.loan.dto;

/**
 * Response containing loan application identifiers and status.
 */
public record ApplyLoanResponse(
    String loanId,
    String userId,
    String userName,
    String organizationName,
    String loanStatus
) {}
