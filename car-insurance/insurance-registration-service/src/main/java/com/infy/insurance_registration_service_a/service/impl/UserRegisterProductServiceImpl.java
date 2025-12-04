package com.infy.insurance_registration_service_a.service.impl;

import com.infy.insurance_registration_service_a.dto.UserRegisterProductDTO;
import com.infy.insurance_registration_service_a.entity.UserRegister;
import com.infy.insurance_registration_service_a.entity.UserRegisterProduct;
import com.infy.insurance_registration_service_a.mapper.UserRegisterProductMapper;
import com.infy.insurance_registration_service_a.repository.UserRegisterProductRepository;
import com.infy.insurance_registration_service_a.repository.UserRegisterRepository;
import com.infy.insurance_registration_service_a.service.UserRegisterProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of UserRegisterProductService
 * Handles business logic for product registration operations
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserRegisterProductServiceImpl implements UserRegisterProductService {

    private final UserRegisterProductRepository userRegisterProductRepository;
    private final UserRegisterRepository userRegisterRepository;
    private final UserRegisterProductMapper userRegisterProductMapper;

    private final Logger log = LoggerFactory.getLogger(UserRegisterProductServiceImpl.class);

    @Override
    public UserRegisterProductDTO registerProduct(UserRegisterProductDTO userRegisterProductDTO) {
        log.info("Registering product {} for customer {}", 
                userRegisterProductDTO.getProductId(), 
                userRegisterProductDTO.getCustomerId());

        // Validate customer exists
        UserRegister userRegister = userRegisterRepository.findById(userRegisterProductDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + userRegisterProductDTO.getCustomerId()));

        // Check if customer has already registered for this product
        if (userRegisterProductRepository.existsByCustomerIdAndProductId(
                userRegisterProductDTO.getCustomerId(), 
                userRegisterProductDTO.getProductId())) {
            throw new IllegalArgumentException("Customer has already registered for this product");
        }

        UserRegisterProduct userRegisterProduct = userRegisterProductMapper.toEntity(userRegisterProductDTO, userRegister);
        
        // Set registration date if not provided
        if (userRegisterProduct.getRegistrationDate() == null) {
            userRegisterProduct.setRegistrationDate(LocalDateTime.now());
        }

        UserRegisterProduct savedEntity = userRegisterProductRepository.save(userRegisterProduct);

        log.info("Successfully registered product with registration ID: {}", savedEntity.getProductRegistrationId());
        return userRegisterProductMapper.toDTO(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserRegisterProductDTO getProductRegistrationById(Long productRegistrationId) {
        log.info("Fetching product registration with ID: {}", productRegistrationId);

        UserRegisterProduct userRegisterProduct = userRegisterProductRepository.findById(productRegistrationId)
                .orElseThrow(() -> new RuntimeException("Product registration not found with ID: " + productRegistrationId));

        return userRegisterProductMapper.toDTO(userRegisterProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRegisterProductDTO> getAllProductRegistrations() {
        log.info("Fetching all product registrations");

        return userRegisterProductRepository.findAll().stream()
                .map(userRegisterProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRegisterProductDTO> getProductRegistrationsByCustomerId(Long customerId) {
        log.info("Fetching product registrations for customer ID: {}", customerId);

        // Verify customer exists
        if (!userRegisterRepository.existsById(customerId)) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }

        return userRegisterProductRepository.findByCustomerId(customerId).stream()
                .map(userRegisterProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRegisterProductDTO> getProductRegistrationsByProductId(Long productId) {
        log.info("Fetching product registrations for product ID: {}", productId);

        return userRegisterProductRepository.findByProductId(productId).stream()
                .map(userRegisterProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserRegisterProductDTO updateProductRegistration(Long productRegistrationId, UserRegisterProductDTO userRegisterProductDTO) {
        log.info("Updating product registration with ID: {}", productRegistrationId);

        UserRegisterProduct existingEntity = userRegisterProductRepository.findById(productRegistrationId)
                .orElseThrow(() -> new RuntimeException("Product registration not found with ID: " + productRegistrationId));

        // Check if product is being changed and if customer already has the new product
        if (!existingEntity.getProductId().equals(userRegisterProductDTO.getProductId())) {
            Long customerId = existingEntity.getUserRegister().getCustomerId();
            if (userRegisterProductRepository.existsByCustomerIdAndProductId(customerId, userRegisterProductDTO.getProductId())) {
                throw new IllegalArgumentException("Customer has already registered for the new product");
            }
        }

        userRegisterProductMapper.updateEntityFromDTO(userRegisterProductDTO, existingEntity);
        UserRegisterProduct updatedEntity = userRegisterProductRepository.save(existingEntity);

        log.info("Successfully updated product registration with ID: {}", productRegistrationId);
        return userRegisterProductMapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteProductRegistration(Long productRegistrationId) {
        log.info("Deleting product registration with ID: {}", productRegistrationId);

        if (!userRegisterProductRepository.existsById(productRegistrationId)) {
            throw new RuntimeException("Product registration not found with ID: " + productRegistrationId);
        }

        userRegisterProductRepository.deleteById(productRegistrationId);
        log.info("Successfully deleted product registration with ID: {}", productRegistrationId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isProductRegistered(Long customerId, Long productId) {
        return userRegisterProductRepository.existsByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getRegistrationCountByCustomerId(Long customerId) {
        log.info("Counting registrations for customer ID: {}", customerId);
        return userRegisterProductRepository.countByCustomerId(customerId);
    }
}
