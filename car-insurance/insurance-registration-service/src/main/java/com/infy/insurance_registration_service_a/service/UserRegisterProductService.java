package com.infy.insurance_registration_service_a.service;

import com.infy.insurance_registration_service_a.dto.*;

import java.util.List;

/**
 * Service interface for UserRegisterProduct operations
 */
public interface UserRegisterProductService {

    /**
     * Register a customer for a product
     *
     * @param userRegisterProductDTO Product registration data
     * @return Created UserRegisterProductDTO
     */
    UserRegisterProductDTO registerProduct(UserRegisterProductDTO userRegisterProductDTO);

    /**
     * Get a product registration by ID
     *
     * @param productRegistrationId Product registration ID
     * @return UserRegisterProductDTO if found
     */
    UserRegisterProductDTO getProductRegistrationById(Long productRegistrationId);

    /**
     * Get all product registrations
     *
     * @return List of UserRegisterProductDTO
     */
    List<UserRegisterProductDTO> getAllProductRegistrations();

    /**
     * Get all product registrations for a specific customer
     *
     * @param customerId Customer ID
     * @return List of UserRegisterProductDTO
     */
    List<UserRegisterProductDTO> getProductRegistrationsByCustomerId(Long customerId);

    /**
     * Get all registrations for a specific product
     *
     * @param productId Product ID
     * @return List of UserRegisterProductDTO
     */
    List<UserRegisterProductDTO> getProductRegistrationsByProductId(Long productId);

    /**
     * Update an existing product registration
     *
     * @param productRegistrationId Product registration ID
     * @param userRegisterProductDTO Updated product registration data
     * @return Updated UserRegisterProductDTO
     */
    UserRegisterProductDTO updateProductRegistration(Long productRegistrationId, UserRegisterProductDTO userRegisterProductDTO);

    /**
     * Delete a product registration
     *
     * @param productRegistrationId Product registration ID to delete
     */
    void deleteProductRegistration(Long productRegistrationId);

    /**
     * Check if a customer has already registered for a product
     *
     * @param customerId Customer ID
     * @param productId  Product ID
     * @return true if registration exists, false otherwise
     */
    boolean isProductRegistered(Long customerId, Long productId);

    /**
     * Get total number of registrations for a customer
     *
     * @param customerId Customer ID
     * @return Number of registrations
     */
    long getRegistrationCountByCustomerId(Long customerId);
}
