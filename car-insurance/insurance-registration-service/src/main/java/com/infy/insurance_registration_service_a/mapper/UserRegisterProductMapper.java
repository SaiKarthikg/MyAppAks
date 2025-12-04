package com.infy.insurance_registration_service_a.mapper;

import com.infy.insurance_registration_service_a.dto.UserRegisterProductDTO;
import com.infy.insurance_registration_service_a.entity.UserRegisterProduct;
import com.infy.insurance_registration_service_a.entity.UserRegister;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between UserRegisterProduct entity and UserRegisterProductDTO
 */
@Component
public class UserRegisterProductMapper implements EntityMapper<UserRegisterProduct, UserRegisterProductDTO> {

    @Override
    public UserRegisterProductDTO toDTO(UserRegisterProduct entity) {
        if (entity == null) {
            return null;
        }

        UserRegisterProductDTO dto = new UserRegisterProductDTO();
        dto.setProductRegistrationId(entity.getProductRegistrationId());
        dto.setProductId(entity.getProductId());
        dto.setProductName(entity.getProductName());
        dto.setPrice(entity.getPrice());
        dto.setBenefits(entity.getBenefits());
        dto.setRegistrationDate(entity.getRegistrationDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        
        if (entity.getUserRegister() != null) {
            dto.setCustomerId(entity.getUserRegister().getCustomerId());
        }

        return dto;
    }

    @Override
    public UserRegisterProduct toEntity(UserRegisterProductDTO dto) {
        if (dto == null) {
            return null;
        }

        UserRegisterProduct entity = new UserRegisterProduct();
        entity.setProductRegistrationId(dto.getProductRegistrationId());
        entity.setProductId(dto.getProductId());
        entity.setProductName(dto.getProductName());
        entity.setPrice(dto.getPrice());
        entity.setBenefits(dto.getBenefits());
        entity.setRegistrationDate(dto.getRegistrationDate());
        // createdAt and updatedAt are handled by @CreationTimestamp and @UpdateTimestamp

        return entity;
    }

    /**
     * Convert UserRegisterProductDTO to UserRegisterProduct entity with UserRegister reference
     * Additional method to set the relationship
     *
     * @param dto          UserRegisterProductDTO
     * @param userRegister UserRegister entity to associate with
     * @return UserRegisterProduct entity
     */
    public UserRegisterProduct toEntity(UserRegisterProductDTO dto, UserRegister userRegister) {
        UserRegisterProduct entity = toEntity(dto);
        if (entity != null) {
            entity.setUserRegister(userRegister);
        }
        return entity;
    }

    @Override
    public void updateEntityFromDTO(UserRegisterProductDTO dto, UserRegisterProduct entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setProductId(dto.getProductId());
        entity.setProductName(dto.getProductName());
        entity.setPrice(dto.getPrice());
        entity.setBenefits(dto.getBenefits());
        entity.setRegistrationDate(dto.getRegistrationDate());
    }
}
