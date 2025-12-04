package com.infy.insurance_registration_service_a.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity class representing the junction table linking customers to insurance products
 * Maps to user_register_product table in the database
 */
@Entity
@Table(name = "user_register_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_registration_id")
    private Long productRegistrationId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "benefits", nullable = false, columnDefinition = "TEXT")
    private String benefits;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private UserRegister userRegister;

    public Long getCustomerId() {
        return userRegister != null ? userRegister.getCustomerId() : null;
    }
}
