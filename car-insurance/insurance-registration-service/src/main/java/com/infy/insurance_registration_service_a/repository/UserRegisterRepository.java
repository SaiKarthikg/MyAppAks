package com.infy.insurance_registration_service_a.repository;

import com.infy.insurance_registration_service_a.entity.UserRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for UserRegister entity
 * Provides database operations for customer registration
 */
@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegister, Long> {

    /**
     * Find a user registration by email ID
     *
     * @param emailId Email ID to search for
     * @return Optional containing UserRegister if found
     */
    Optional<UserRegister> findByEmailId(String emailId);

    /**
     * Find a user registration by car number
     *
     * @param carNumber Car number to search for
     * @return Optional containing UserRegister if found
     */
    Optional<UserRegister> findByCarNumber(String carNumber);

    /**
     * Check if a user exists by email ID
     *
     * @param emailId Email ID to check
     * @return true if exists, false otherwise
     */
    boolean existsByEmailId(String emailId);

    /**
     * Check if a user exists by car number
     *
     * @param carNumber Car number to check
     * @return true if exists, false otherwise
     */
    boolean existsByCarNumber(String carNumber);
}
