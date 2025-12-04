package com.ebrd.insurance.service;

import com.ebrd.insurance.dto.InsuranceApplyRequestDto;
import com.ebrd.insurance.dto.InsuranceApplyResponseDto;
import com.ebrd.insurance.dto.ResponseDto;
import com.ebrd.insurance.entity.UserRegister;
import com.ebrd.insurance.repository.UserRegisterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InsuranceServiceTest {
    @Mock
    private UserRegisterRepository repository;

    @InjectMocks
    private InsuranceService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void applyForInsurance_success() {
        InsuranceApplyRequestDto request = new InsuranceApplyRequestDto(
                "John Doe", "1234567890", "john.doe@example.com", "AB123CD", "Toyota", "Corolla", 2020, LocalDate.now().plusYears(1));
        when(repository.existsByEmailId(request.emailId())).thenReturn(false);
        UserRegister user = new UserRegister();
        user.setCustomerId(1L);
        user.setCustomerName(request.customerName());
        user.setPhoneNumber(request.phoneNumber());
        user.setEmailId(request.emailId());
        user.setCarNumber(request.carNumber());
        user.setManufacturer(request.manufacturer());
        user.setModel(request.model());
        user.setMakingYear(request.makingYear());
        user.setInsuranceExpiryDate(request.insuranceExpiryDate());
        when(repository.save(any(UserRegister.class))).thenReturn(user);
        ResponseDto<InsuranceApplyResponseDto> response = service.applyForInsurance(request);
        InsuranceApplyResponseDto respDto = response.getData();
        assertEquals(user.getCustomerId(), respDto.customerId());
        assertEquals(user.getEmailId(), respDto.emailId());
    }

    @Test
    void applyForInsurance_duplicateEmail() {
        InsuranceApplyRequestDto request = new InsuranceApplyRequestDto(
                "John Doe", "1234567890", "john.doe@example.com", "AB123CD", "Toyota", "Corolla", 2020, LocalDate.now().plusYears(1));
        when(repository.existsByEmailId(request.emailId())).thenReturn(true);
        ResponseDto<InsuranceApplyResponseDto> response = service.applyForInsurance(request);
        assertEquals("Email already registered.", response.getMessage());
    }

    @Test
    void applyForInsurance_exception() {
        InsuranceApplyRequestDto request = new InsuranceApplyRequestDto(
                "John Doe", "1234567890", "john.doe@example.com", "AB123CD", "Toyota", "Corolla", 2020, LocalDate.now().plusYears(1));
        when(repository.existsByEmailId(request.emailId())).thenReturn(false);
        when(repository.save(any(UserRegister.class))).thenThrow(new RuntimeException("DB error"));
        ResponseDto<InsuranceApplyResponseDto> response = service.applyForInsurance(request);
        assertTrue(response.getMessage().contains("Failed to apply for insurance"));
    }
}
