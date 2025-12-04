package com.infy.insurance_registration_service_a.repository;

import com.infy.insurance_registration_service_a.entity.UserRegisterProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for UserRegisterProduct entity
 * Provides database operations for product registrations
 */
@Repository
public interface UserRegisterProductRepository extends JpaRepository<UserRegisterProduct, Long> {

    /**
     * Find all product registrations for a specific customer
     *
     * @param customerId Customer ID
     * @return List of UserRegisterProduct
     */
    @Query("SELECT urp FROM UserRegisterProduct urp WHERE urp.userRegister.customerId = :customerId")
    List<UserRegisterProduct> findByCustomerId(@Param("customerId") Long customerId);

    /**
     * Find all registrations for a specific product
     *
     * @param productId Product ID
     * @return List of UserRegisterProduct
     */
    List<UserRegisterProduct> findByProductId(Long productId);

    /**
     * Find a specific product registration by customer and product
     *
     * @param customerId Customer ID
     * @param productId  Product ID
     * @return Optional containing UserRegisterProduct if found
     */
    @Query("SELECT urp FROM UserRegisterProduct urp WHERE urp.userRegister.customerId = :customerId AND urp.productId = :productId")
    Optional<UserRegisterProduct> findByCustomerIdAndProductId(
            @Param("customerId") Long customerId,
            @Param("productId") Long productId
    );

    /**
     * Check if a customer has already registered for a product
     *
     * @param customerId Customer ID
     * @param productId  Product ID
     * @return true if registration exists, false otherwise
     */
    @Query("SELECT CASE WHEN COUNT(urp) > 0 THEN true ELSE false END FROM UserRegisterProduct urp WHERE urp.userRegister.customerId = :customerId AND urp.productId = :productId")
    boolean existsByCustomerIdAndProductId(
            @Param("customerId") Long customerId,
            @Param("productId") Long productId
    );

    /**
     * Count total registrations for a customer
     *
     * @param customerId Customer ID
     * @return Number of registrations
     */
    @Query("SELECT COUNT(urp) FROM UserRegisterProduct urp WHERE urp.userRegister.customerId = :customerId")
    long countByCustomerId(@Param("customerId") Long customerId);
}
