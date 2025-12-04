package com.example.banking.registration.repository;

import com.example.banking.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailId(String emailId);
    boolean existsByUserName(String userName);
}

