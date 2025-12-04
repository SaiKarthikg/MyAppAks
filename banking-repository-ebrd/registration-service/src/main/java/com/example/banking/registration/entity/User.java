package com.example.banking.registration.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents an application user.
 * Table name: users, columns in snake_case.
 */
@Entity
@Table(name = "loan_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User entity representing a registered user")
public class User {

    /** Primary key (auto). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /** Unique user name for login. */
    @Column(name = "user_name", nullable = false, unique = true, length = 100)
    private String userName;

    /** Email address (unique). */
    @Column(name = "email_id", nullable = false, unique = true, length = 200)
    private String emailId;

    /** Phone number. */
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    /** Organization name. */
    @Column(name = "organization_name", nullable = false, length = 200)
    private String organizationName;

    /** TaxId or GSTID. */
    @Column(name = "tax_id", nullable = false, length = 50)
    private String taxIId;

    /** Password (hashed). */
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    /** Confirm password (not persisted, transient). */
    @Transient
    private String confirmPassword;
}
