package com.example.banking.loan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Loan application record.
 * Stores applicant linkage and up to two supporting document file paths.
 */
@Entity
@Table(name = "loan_application")
@Getter @Setter
public class LoanApplication {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "loan_id", nullable = false, unique = true, length = 36)
  private String loanId;

  @Column(name = "user_id", nullable = false, length = 36)
  private String userId;

  @Column(name = "user_name", nullable = false, length = 100)
  private String userName;

  @Column(name = "organization_name", nullable = false, length = 200)
  private String organizationName;

  @Column(name = "loan_type", nullable = false, length = 50)
  private String loanType;

  @Column(name = "required_amount", nullable = false, precision = 15, scale = 2)
  private BigDecimal requiredAmount;

  @Column(name = "loan_purpose", length = 1000)
  private String loanPurpose;

  @Column(name = "loan_status", nullable = false, length = 50)
  private String loanStatus = "Submitted";

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  // Supporting docs (file paths under resources/docs)
  @Column(name = "doc1_path")
  private String doc1Path;

  @Column(name = "doc2_path")
  private String doc2Path;
}
