/*
package com.example.banking.registration.service;

import com.example.banking.registration.dto.LoginRequest;
import com.example.banking.registration.dto.UserRequest;
import com.example.banking.registration.dto.UserResponse;
import com.example.banking.registration.entity.User;
import com.example.banking.registration.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsersServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUserSuccess() {
        UserRequest request = new UserRequest();
        request.setUserName("testuser");
        request.setEmailId("test@example.com");
        request.setPhoneNumber("1234567890");
        request.setOrganizationName("TestOrg");
        request.setTaxId("GST12345");
        request.setPassword("password");
        request.setConfirmPassword("password");
        User user = new User();
        user.setUserId(1L);
        user.setUserName("testuser");
        user.setEmailId("test@example.com");
        user.setPhoneNumber("1234567890");
        user.setOrganizationName("TestOrg");
        user.setTaxIId("GST12345");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        when(userRepository.existsByEmailId("test@example.com")).thenReturn(false);
        when(userRepository.existsByUserName("testuser")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse response = usersService.registerUser(request);
        assertEquals("1", response.userId());
        assertEquals("testuser", response.userName());
    }

    @Test
    void testRegisterUserPasswordMismatch() {
        UserRequest request = new UserRequest();
        request.setUserName("testuser");
        request.setEmailId("test@example.com");
        request.setPhoneNumber("1234567890");
        request.setOrganizationName("TestOrg");
        request.setTaxId("GST12345");
        request.setPassword("password");
        request.setConfirmPassword("wrongpassword");
        assertThrows(IllegalArgumentException.class, () -> usersService.registerUser(request));
    }

    @Test
    void testRegisterUserEmailExists() {
        UserRequest request = new UserRequest();
        request.setUserName("testuser");
        request.setEmailId("test@example.com");
        request.setPhoneNumber("1234567890");
        request.setOrganizationName("TestOrg");
        request.setTaxId("GST12345");
        request.setPassword("password");
        request.setConfirmPassword("password");
        when(userRepository.existsByEmailId("test@example.com")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> usersService.registerUser(request));
    }

    @Test
    void testRegisterUserUsernameExists() {
        UserRequest request = new UserRequest();
        request.setUserName("testuser");
        request.setEmailId("test@example.com");
        request.setPhoneNumber("1234567890");
        request.setOrganizationName("TestOrg");
        request.setTaxId("GST12345");
        request.setPassword("password");
        request.setConfirmPassword("password");
        when(userRepository.existsByEmailId("test@example.com")).thenReturn(false);
        when(userRepository.existsByUserName("testuser")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> usersService.registerUser(request));
    }

    @Test
    void testGetUserDetailsByIdSuccess() {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("testuser");
        user.setEmailId("test@example.com");
        user.setPhoneNumber("1234567890");
        user.setOrganizationName("TestOrg");
        user.setTaxIId("GST12345");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserResponse response = usersService.getUserDetailsById(1L);
        assertEquals("1", response.userId());
        assertEquals("testuser", response.userName());
    }

    @Test
    void testGetUserDetailsByIdNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> usersService.getUserDetailsById(2L));
    }

    @Test
    void testLoginSuccess() {
        LoginRequest request = new LoginRequest();
        request.setEmailId("test@example.com");
        request.setPassword("password");
        User user = new User();
        user.setUserId(1L);
        user.setUserName("testuser");
        user.setEmailId("test@example.com");
        user.setPhoneNumber("1234567890");
        user.setOrganizationName("TestOrg");
        user.setTaxIId("GST12345");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        UserResponse response = usersService.login(request);
        assertEquals("1", response.userId());
        assertEquals("testuser", response.userName());
    }

    @Test
    void testLoginFailureWrongEmail() {
        LoginRequest request = new LoginRequest();
        request.setEmailId("wrong@example.com");
        request.setPassword("password");
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(IllegalArgumentException.class, () -> usersService.login(request));
    }

    @Test
    void testLoginFailureWrongPassword() {
        LoginRequest request = new LoginRequest();
        request.setEmailId("test@example.com");
        request.setPassword("wrongpass");
        User user = new User();
        user.setUserId(1L);
        user.setUserName("testuser");
        user.setEmailId("test@example.com");
        user.setPhoneNumber("1234567890");
        user.setOrganizationName("TestOrg");
        user.setTaxIId("GST12345");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        assertThrows(IllegalArgumentException.class, () -> usersService.login(request));
    }
}

*/
