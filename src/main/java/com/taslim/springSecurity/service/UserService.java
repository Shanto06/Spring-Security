package com.taslim.springSecurity.service;

import com.taslim.springSecurity.dto.*;
import com.taslim.springSecurity.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {

    /**
     * Registers a new user.
     *
     * @param requestModel The request model containing user details.
     * @return Map containing the authentication response and the ID of the saved user.
     */
    Map<String, Object> register(UserRequestDto requestModel);

    /**
     * Performs user authentication and generates an authentication token.
     *
     * @param authenticationDto The request model containing user credentials.
     * @return AuthenticationResponse containing the generated token.
     */
    AuthenticationResponse login(AuthenticationDto authenticationDto);

    /**
     * Retrieves a list of all users.
     *
     * @return List of UserEntity.
     */
    List<UserEntity> getAllUsers();

    /**
     * Resets the password for a user.
     *
     * @param userId              The ID of the user for password reset.
     * @param userRequestModel    The request model containing the new password.
     * @return ResponseEntity containing the updated UserEntity.
     */
    ResponseEntity<Object> reseatPassword(Long userId, UserRequestDto userRequestModel);

    /**
     * Changes the active status of a user.
     *
     * @param userId The ID of the user to change the status.
     * @return ResponseEntity with a confirmation message.
     */
    ResponseEntity<Object> statusChange(Long userId);
}
