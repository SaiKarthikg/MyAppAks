package com.infy.insurance_registration_service_a.service.impl;

import com.infy.insurance_registration_service_a.dto.UserRegisterDTO;
import com.infy.insurance_registration_service_a.entity.UserRegister;
import com.infy.insurance_registration_service_a.mapper.UserRegisterMapper;
import com.infy.insurance_registration_service_a.repository.UserRegisterRepository;
import com.infy.insurance_registration_service_a.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of UserRegisterService
 * Handles business logic for customer registration operations
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRegisterRepository userRegisterRepository;
    private final UserRegisterMapper userRegisterMapper;

    @Override
    public UserRegisterDTO createUserRegister(UserRegisterDTO userRegisterDTO) {
        log.info("Creating new user registration for email: {}", userRegisterDTO.getEmailId());

        // Check if email already exists
        if (userRegisterRepository.existsByEmailId(userRegisterDTO.getEmailId())) {
            throw new IllegalArgumentException("Email already exists: " + userRegisterDTO.getEmailId());
        }

        // Check if car number already exists
        if (userRegisterRepository.existsByCarNumber(userRegisterDTO.getCarNumber())) {
            throw new IllegalArgumentException("Car number already exists: " + userRegisterDTO.getCarNumber());
        }

        UserRegister userRegister = userRegisterMapper.toEntity(userRegisterDTO);
        UserRegister savedEntity = userRegisterRepository.save(userRegister);

        log.info("Successfully created user registration with ID: {}", savedEntity.getCustomerId());
        return userRegisterMapper.toDTO(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserRegisterDTO getUserRegisterById(Long customerId) {
        log.info("Fetching user registration with ID: {}", customerId);

        UserRegister userRegister = userRegisterRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User registration not found with ID: " + customerId));

        return userRegisterMapper.toDTO(userRegister);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRegisterDTO> getAllUserRegisters() {
        log.info("Fetching all user registrations");

        return userRegisterRepository.findAll().stream()
                .map(userRegisterMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserRegisterDTO updateUserRegister(Long customerId, UserRegisterDTO userRegisterDTO) {
        log.info("Updating user registration with ID: {}", customerId);

        UserRegister existingEntity = userRegisterRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User registration not found with ID: " + customerId));

        // Check if email is being changed and if new email already exists
        if (!existingEntity.getEmailId().equals(userRegisterDTO.getEmailId())) {
            if (userRegisterRepository.existsByEmailId(userRegisterDTO.getEmailId())) {
                throw new IllegalArgumentException("Email already exists: " + userRegisterDTO.getEmailId());
            }
        }

        // Check if car number is being changed and if new car number already exists
        if (!existingEntity.getCarNumber().equals(userRegisterDTO.getCarNumber())) {
            if (userRegisterRepository.existsByCarNumber(userRegisterDTO.getCarNumber())) {
                throw new IllegalArgumentException("Car number already exists: " + userRegisterDTO.getCarNumber());
            }
        }

        userRegisterMapper.updateEntityFromDTO(userRegisterDTO, existingEntity);
        UserRegister updatedEntity = userRegisterRepository.save(existingEntity);

        log.info("Successfully updated user registration with ID: {}", customerId);
        return userRegisterMapper.toDTO(updatedEntity);
    }

    @Override
    public void deleteUserRegister(Long customerId) {
        log.info("Deleting user registration with ID: {}", customerId);

        if (!userRegisterRepository.existsById(customerId)) {
            throw new RuntimeException("User registration not found with ID: " + customerId);
        }

        userRegisterRepository.deleteById(customerId);
        log.info("Successfully deleted user registration with ID: {}", customerId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserRegisterDTO getUserRegisterByEmail(String emailId) {
        log.info("Fetching user registration with email: {}", emailId);

        UserRegister userRegister = userRegisterRepository.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("User registration not found with email: " + emailId));

        return userRegisterMapper.toDTO(userRegister);
    }

    @Override
    @Transactional(readOnly = true)
    public UserRegisterDTO getUserRegisterByCarNumber(String carNumber) {
        log.info("Fetching user registration with car number: {}", carNumber);

        UserRegister userRegister = userRegisterRepository.findByCarNumber(carNumber)
                .orElseThrow(() -> new RuntimeException("User registration not found with car number: " + carNumber));

        return userRegisterMapper.toDTO(userRegister);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExists(String emailId) {
        return userRegisterRepository.existsByEmailId(emailId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isCarNumberExists(String carNumber) {
        return userRegisterRepository.existsByCarNumber(carNumber);
    }
}
