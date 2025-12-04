/*
package com.example.banking.registration.controller;

import com.example.banking.registration.dto.LoginRequest;
import com.example.banking.registration.dto.UserRequest;
import com.example.banking.registration.dto.UserResponse;
import com.example.banking.registration.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsersControllerTest {

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() {
        LoginRequest request = new LoginRequest();
        request.setEmailId("test@example.com");
        request.setPassword("password");
        UserResponse userResponse = new UserResponse("1", "testuser", "test@example.com", "1234567890", "TestOrg", "GST12345");
        when(usersService.login(any(LoginRequest.class))).thenReturn(userResponse);

        ResponseEntity<?> response = usersController.login(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testLoginFailure() {
        LoginRequest request = new LoginRequest();
        request.setEmailId("wrong@example.com");
        request.setPassword("wrongpass");
        when(usersService.login(any(LoginRequest.class))).thenThrow(new IllegalArgumentException("Invalid email or password"));

        ResponseEntity<?> response = usersController.login(request);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
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
        UserResponse userResponse = new UserResponse("1", "testuser", "test@example.com", "1234567890", "TestOrg", "GST12345");
        when(usersService.registerUser(any(UserRequest.class))).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = usersController.registerUser(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("1", response.getBody().userId());
        assertEquals("testuser", response.getBody().userName());
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
        when(usersService.registerUser(any(UserRequest.class))).thenThrow(new IllegalArgumentException("Passwords do not match"));

        assertThrows(IllegalArgumentException.class, () -> usersController.registerUser(request));
    }
}
*/
