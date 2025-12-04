package com.ebrd.insurance.controller;

import com.ebrd.insurance.dto.InsuranceApplyRequestDto;
import com.ebrd.insurance.dto.InsuranceApplyResponseDto;
import com.ebrd.insurance.dto.ResponseDto;
import com.ebrd.insurance.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance/")
@Tag(name = "Insurance API")
public class InsuranceController {

    private final InsuranceService service;

    public InsuranceController(InsuranceService service) {
        this.service = service;
    }

    @Operation(summary = "Apply for insurance")
    @PostMapping("applyForInsurance")
    public ResponseEntity<ResponseDto<String>> applyForInsurance(@RequestBody InsuranceApplyRequestDto request) {
        ResponseDto response = service.applyForInsurance(request);
        return ResponseEntity.status(200).body(response);
    }




}

