package com.infy.insurance_registration_service_a.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity class representing customer registration information
 * Maps to user_register table in the database
 */
@Entity
@Table(name = "user_register")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "car_number", nullable = false, length = 50)
    private String carNumber;

    @Column(name = "manufacturer", nullable = false, length = 100)
    private String manufacturer;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "making_year", nullable = false)
    private Integer makingYear;

    @Column(name = "insurance_expiry_date")
    private LocalDate insuranceExpiryDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userRegister", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRegisterProduct> userRegisterProducts;

    public Long getCustomerId() {
        return customerId;
    }
}
