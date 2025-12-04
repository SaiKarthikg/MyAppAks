package com.example.banking.registration.repository;

import com.example.banking.registration.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * JPA repository for AppUser CRUD and lookups.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  Optional<AppUser> findByUserName(String userName);
  boolean existsByUserName(String userName);
  boolean existsByEmailId(String emailId);
}
