package com.infy.insurance_registration_service_a.service;

import com.infy.insurance_registration_service_a.dto.UserRegisterDTO;

import java.util.List;

/**
 * Service interface for UserRegister operations
 */
public interface UserRegisterService {

    /**
     * Create a new customer registration
     *
     * @param userRegisterDTO Customer registration data
     * @return Created UserRegisterDTO
     */
    UserRegisterDTO createUserRegister(UserRegisterDTO userRegisterDTO);

    /**
     * Get a customer registration by ID
     *
     * @param customerId Customer ID
     * @return UserRegisterDTO if found
     */
    UserRegisterDTO getUserRegisterById(Long customerId);

    /**
     * Get all customer registrations
     *
     * @return List of UserRegisterDTO
     */
    List<UserRegisterDTO> getAllUserRegisters();

    /**
     * Update an existing customer registration
     *
     * @param customerId      Customer ID to update
     * @param userRegisterDTO Updated customer data
     * @return Updated UserRegisterDTO
     */
    UserRegisterDTO updateUserRegister(Long customerId, UserRegisterDTO userRegisterDTO);

    /**
     * Delete a customer registration
     *
     * @param customerId Customer ID to delete
     */
    void deleteUserRegister(Long customerId);

    /**
     * Get a customer registration by email
     *
     * @param emailId Email ID
     * @return UserRegisterDTO if found
     */
    UserRegisterDTO getUserRegisterByEmail(String emailId);

    /**
     * Get a customer registration by car number
     *
     * @param carNumber Car number
     * @return UserRegisterDTO if found
     */
    UserRegisterDTO getUserRegisterByCarNumber(String carNumber);

    /**
     * Check if email already exists
     *
     * @param emailId Email ID to check
     * @return true if exists, false otherwise
     */
    boolean isEmailExists(String emailId);

    /**
     * Check if car number already exists
     *
     * @param carNumber Car number to check
     * @return true if exists, false otherwise
     */
    boolean isCarNumberExists(String carNumber);
}
