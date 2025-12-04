package com.example.banking.loan.controller;

import com.example.banking.loan.dto.ApplyLoanRequest;
import com.example.banking.loan.dto.ApplyLoanResponse;
import com.example.banking.loan.entity.LoanApplication;
import com.example.banking.loan.service.LoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST endpoints for loan applications.
 * Uploaded files are stored under loan-service/src/main/resources/docs/{loanId}/.
 */
@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

  private final LoanService service;

  public LoanController(LoanService service) {
    this.service = service;
  }

  /** Apply for a loan with up to two supporting documents. */
  @PostMapping(value = "/apply", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<ApplyLoanResponse> applyLoan( @Valid @RequestBody ApplyLoanRequest applyLoanRequest,
      @RequestPart(name = "supportingDocs", required = false) List<MultipartFile> supportingDocs
  ) {
    return service.apply(applyLoanRequest, supportingDocs)
        .map(app -> new ApplyLoanResponse(
            app.getLoanId(), app.getUserId(), app.getUserName(),
            app.getOrganizationName(), app.getLoanStatus()
        ));
  }

    @GetMapping(value = "/getLoansByUserId/{loanId}",consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public List<LoanApplication> getLoansByUserId(@Valid @PathVariable("loanId")  String loanId) {
    return service.getLoansByUserId(loanId);

  }
}
