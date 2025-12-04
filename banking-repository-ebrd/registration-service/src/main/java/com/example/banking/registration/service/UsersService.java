package com.example.banking.registration.service;

import com.example.banking.registration.dto.LoginRequest;
import com.example.banking.registration.dto.UserRequest;
import com.example.banking.registration.dto.UserResponse;
import com.example.banking.registration.entity.User;
import com.example.banking.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponse registerUser(UserRequest dto) {

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (userRepository.existsByEmailId(dto.getEmailId())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepository.existsByUserName(dto.getUserName())) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmailId(dto.getEmailId());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setOrganizationName(dto.getOrganizationName());
        user.setTaxIId(dto.getTaxId());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User savedUser = userRepository.save(user);
        return new UserResponse(
                savedUser.getUserId().toString(),
                savedUser.getUserName(),
                savedUser.getEmailId(),
                savedUser.getPhoneNumber(),
                savedUser.getOrganizationName(),
                savedUser.getTaxIId()
        );
    }

    public UserResponse getUserDetailsById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = userOpt.get();
        return new UserResponse(
            user.getUserId().toString(),
            user.getUserName(),
            user.getEmailId(),
            user.getPhoneNumber(),
            user.getOrganizationName(),
            user.getTaxIId()
        );
    }

    public UserResponse login(LoginRequest loginRequest) {

        User user = userRepository.findAll().stream()
            .filter(u -> u.getEmailId().equals(loginRequest.getEmailId()))
            .findFirst()
            .orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return new UserResponse(
            user.getUserId().toString(),
            user.getUserName(),
            user.getEmailId(),
            user.getPhoneNumber(),
            user.getOrganizationName(),
            user.getTaxIId());
    }
}
