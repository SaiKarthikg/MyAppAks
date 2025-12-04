package com.example.banking.loan.repository;

import com.example.banking.loan.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA repository for LoanApplication.
 */
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    /**
     * Find all loan applications by userId.
     */
    List<LoanApplication> findAllByUserId(String userId);
}
