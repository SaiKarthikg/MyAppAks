package com.ebrd.insurance.repository;

import com.ebrd.insurance.entity.UserRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegisterRepository extends JpaRepository<UserRegister, String> {
    boolean existsByEmailId(String s);
}

