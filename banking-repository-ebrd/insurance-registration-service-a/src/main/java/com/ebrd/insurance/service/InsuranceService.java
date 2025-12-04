package com.ebrd.insurance.service;

import com.ebrd.insurance.dto.InsuranceApplyRequestDto;
import com.ebrd.insurance.dto.InsuranceApplyResponseDto;
import com.ebrd.insurance.dto.ResponseDto;
import com.ebrd.insurance.entity.UserRegister;
import com.ebrd.insurance.repository.UserRegisterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsuranceService {
    private final UserRegisterRepository repository;

    public InsuranceService(UserRegisterRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseDto applyForInsurance(InsuranceApplyRequestDto request) {
        if (repository.existsByEmailId(request.emailId())) {
            return ResponseDto.asFailure("Email already registered.");
        }
        try {
            UserRegister user = new UserRegister();
            user.setCustomerName(request.customerName());
            user.setPhoneNumber(request.phoneNumber());
            user.setEmailId(request.emailId());
            user.setCarNumber(request.carNumber());
            user.setManufacturer(request.manufacturer());
            user.setModel(request.model());
            user.setMakingYear(request.makingYear());
            user.setInsuranceExpiryDate(request.insuranceExpiryDate());
            UserRegister saved = repository.save(user);
            return ResponseDto.asSuccess(new InsuranceApplyResponseDto(
                saved.getCustomerId(),
                saved.getCustomerName(),
                saved.getPhoneNumber(),
                saved.getEmailId(),
                saved.getCarNumber(),
                saved.getManufacturer(),
                saved.getModel(),
                saved.getMakingYear(),
                saved.getInsuranceExpiryDate()));
        } catch (Exception e) {
            return ResponseDto.asFailure("Failed to apply for insurance: " + e.getMessage());
        }
    }
}
