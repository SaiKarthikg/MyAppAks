package com.example.banking.registration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an application user.
 * Table name: app_users, columns in snake_case.
 */
@Entity
@Table(name = "app_users")
@Getter @Setter
public class AppUser {
  /** Primary key (auto). */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** Globally unique user identifier. */
  @Column(name = "user_id", nullable = false, unique = true, length = 36)
  private String userId;

  /** Unique user name for login. */
  @Column(name = "user_name", nullable = false, unique = true, length = 100)
  private String userName;

  /** Organization name. */
  @Column(name = "organization_name", nullable = false, length = 200)
  private String organizationName;

  /** Email address (unique). */
  @Column(name = "email_id", nullable = false, unique = true, length = 200)
  private String emailId;

  /** BCrypt-hashed password. */
  @Column(name = "password_hash", nullable = false, length = 255)
  private String passwordHash;
}
