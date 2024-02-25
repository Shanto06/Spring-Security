package com.polygon.technology.springSecurity.controllers;

import com.polygon.technology.springSecurity.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.polygon.technology.springSecurity.service.*;
import com.polygon.technology.springSecurity.dto.*;

import java.util.List;

/**
 * Controller handling operations related to user management.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRequestModel requestModel) {
        return ResponseEntity.ok(userService.register(requestModel));
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.login(authenticationRequest);
    }

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/resetPassword/{userId}")
    public ResponseEntity<Object> reseatPassword(@PathVariable Long userId, @RequestBody UserRequestModel userRequestModel) {
        return userService.reseatPassword(userId, userRequestModel);
    }

    @PostMapping("/status/{userId}")
    public ResponseEntity<Object> statusChange(@PathVariable Long userId) {
        return userService.statusChange(userId);
    }
}
