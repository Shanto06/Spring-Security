package com.taslim.springSecurity.controllers;

import com.taslim.springSecurity.dto.*;
import com.taslim.springSecurity.entity.UserEntity;
import com.taslim.springSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling operations related to user management.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Endpoint for user registration.
     *
     * @param requestModel UserRequestModel containing registration details.
     * @return ResponseEntity containing the result of the registration operation.
     */
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRequestDto requestModel) {
        return ResponseEntity.ok(userService.register(requestModel));
    }

    /**
     * Endpoint for user login.
     *
     * @param authenticationDto AuthenticationRequest containing login credentials.
     * @return AuthenticationResponse containing authentication result.
     */
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationDto authenticationDto) {
        return userService.login(authenticationDto);
    }

    /**
     * Endpoint to retrieve all users.
     *
     * @return List of UserEntity containing all users.
     */
    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Endpoint to reset user password.
     *
     * @param userId         ID of the user whose password needs to be reset.
     * @param userRequestDto UserRequestModel containing the new password.
     * @return ResponseEntity containing the result of the password reset operation.
     */
    @PostMapping("/resetPassword/{userId}")
    public ResponseEntity<Object> reseatPassword(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDto) {
        return userService.reseatPassword(userId, userRequestDto);
    }

    /**
     * Endpoint to change user status.
     *
     * @param userId ID of the user whose status needs to be changed.
     * @return ResponseEntity containing the result of the status change operation.
     */
    @PostMapping("/status/{userId}")
    public ResponseEntity<Object> statusChange(@PathVariable Long userId) {
        return userService.statusChange(userId);
    }
}
