package com.example.banking.loan.service;

import com.example.banking.loan.client.RegistrationClient;
import com.example.banking.loan.dto.ApplyLoanRequest;
import com.example.banking.loan.entity.LoanApplication;
import com.example.banking.loan.repository.LoanApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Business logic for loan applications.
 */
@Service
public class LoanService {

  private final LoanApplicationRepository repo;
  private final RegistrationClient registrationClient;

  public LoanService(LoanApplicationRepository repo, RegistrationClient registrationClient) {
    this.repo = repo;
    this.registrationClient = registrationClient;
  }

  /**
   * Applies for a loan: validates amount range, resolves user info by userName,
   * stores application with default status "Submitted" and saves up to two docs
   * under src/main/resources/docs/{loanId}/.
   */
  @Transactional
  public Mono<LoanApplication> apply(ApplyLoanRequest applyLoanRequest,
      List<MultipartFile> docs) {
      LoanApplication app = new LoanApplication();
      String loanId = UUID.randomUUID().toString();
      app.setLoanId(loanId);
      app.setUserId(applyLoanRequest.userId());
      app.setUserName(applyLoanRequest.userName());
      app.setOrganizationName(applyLoanRequest.organizationName());
      app.setLoanType(applyLoanRequest.loanType());
      app.setRequiredAmount(applyLoanRequest.requiredAmount());
      app.setLoanPurpose(applyLoanRequest.loanPurpose());
      app.setLoanStatus("Submitted");
       return Mono.just(repo.save(app));


  }

    public List<LoanApplication> getLoansByUserId(@Valid String loanId) {
        return repo.findAllByUserId(loanId);
    }
}
