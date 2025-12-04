package com.infy.insurance_registration_service_a.mapper;

import com.infy.insurance_registration_service_a.dto.UserRegisterDTO;
import com.infy.insurance_registration_service_a.entity.UserRegister;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between UserRegister entity and UserRegisterDTO
 */
@Component
public class UserRegisterMapper implements EntityMapper<UserRegister, UserRegisterDTO> {

    @Override
    public UserRegisterDTO toDTO(UserRegister entity) {
        if (entity == null) {
            return null;
        }

        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setCustomerId(entity.getCustomerId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmailId(entity.getEmailId());
        dto.setCarNumber(entity.getCarNumber());
        dto.setManufacturer(entity.getManufacturer());
        dto.setModel(entity.getModel());
        dto.setMakingYear(entity.getMakingYear());
        dto.setInsuranceExpiryDate(entity.getInsuranceExpiryDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    @Override
    public UserRegister toEntity(UserRegisterDTO dto) {
        if (dto == null) {
            return null;
        }

        UserRegister entity = new UserRegister();
        entity.setCustomerId(dto.getCustomerId());
        entity.setCustomerName(dto.getCustomerName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmailId(dto.getEmailId());
        entity.setCarNumber(dto.getCarNumber());
        entity.setManufacturer(dto.getManufacturer());
        entity.setModel(dto.getModel());
        entity.setMakingYear(dto.getMakingYear());
        entity.setInsuranceExpiryDate(dto.getInsuranceExpiryDate());
        // createdAt and updatedAt are handled by @CreationTimestamp and @UpdateTimestamp

        return entity;
    }

    @Override
    public void updateEntityFromDTO(UserRegisterDTO dto, UserRegister entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setCustomerName(dto.getCustomerName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmailId(dto.getEmailId());
        entity.setCarNumber(dto.getCarNumber());
        entity.setManufacturer(dto.getManufacturer());
        entity.setModel(dto.getModel());
        entity.setMakingYear(dto.getMakingYear());
        entity.setInsuranceExpiryDate(dto.getInsuranceExpiryDate());
    }
}
