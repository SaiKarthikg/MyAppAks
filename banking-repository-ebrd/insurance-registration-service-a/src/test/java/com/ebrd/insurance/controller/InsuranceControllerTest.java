package com.ebrd.insurance.controller;

import com.ebrd.insurance.dto.InsuranceApplyRequestDto;
import com.ebrd.insurance.dto.ResponseDto;
import com.ebrd.insurance.service.InsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InsuranceControllerTest {
    @Mock
    private InsuranceService insuranceService;

    @InjectMocks
    private InsuranceController insuranceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void applyForInsurance_success() {
        InsuranceApplyRequestDto request = new InsuranceApplyRequestDto(
                "John Doe", "1234567890", "john.doe@example.com", "AB123CD", "Toyota", "Corolla", 2020, java.time.LocalDate.now().plusYears(1));
        ResponseDto<String> responseDto = ResponseDto.asSuccess("Insurance applied successfully");
        when(insuranceService.applyForInsurance(request)).thenReturn(responseDto);

        ResponseEntity<ResponseDto<String>> response = insuranceController.applyForInsurance(request);
        assertEquals(org.springframework.http.HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(insuranceService, times(1)).applyForInsurance(request);
    }

    @Test
    void applyForInsurance_failure() {
        InsuranceApplyRequestDto request = new InsuranceApplyRequestDto(
                "Jane Doe", "0987654321", "jane.doe@example.com", "XY987ZT", "Honda", "Civic", 2021, java.time.LocalDate.now().plusYears(2));
        ResponseDto<String> responseDto = ResponseDto.asFailure("Email already registered.");
        when(insuranceService.applyForInsurance(request)).thenReturn(responseDto);

        ResponseEntity<ResponseDto<String>> response = insuranceController.applyForInsurance(request);
        assertEquals(org.springframework.http.HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(insuranceService, times(1)).applyForInsurance(request);
    }
}
