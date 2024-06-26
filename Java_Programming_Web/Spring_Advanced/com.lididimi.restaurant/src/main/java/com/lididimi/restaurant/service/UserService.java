package com.lididimi.restaurant.service;

import com.lididimi.restaurant.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<String> register(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<UserWrapper>> getAllUsers();

    ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);

    boolean validatePasswordResetToken(String token);

    void updatePassword(String token, String newPassword);
}
