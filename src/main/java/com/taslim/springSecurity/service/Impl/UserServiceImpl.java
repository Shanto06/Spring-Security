package com.taslim.springSecurity.service.Impl;

import com.taslim.springSecurity.utils.*;
import com.taslim.springSecurity.dto.*;
import com.taslim.springSecurity.entity.*;
import com.taslim.springSecurity.repository.*;
import com.taslim.springSecurity.service.*;
import com.taslim.springSecurity.exception.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Registers a new user.
     *
     * @param requestModel The model containing user details.
     * @return Map containing authentication response and the saved user's ID.
     */
    @Override
    public Map<String, Object> register(UserRequestDto requestModel) {
        UserEntity userExistedAlready = userRepository.findByEmail(requestModel.getEmail());
        if (userExistedAlready != null) {
            throw new UserAlreadyExistException("This Email Already Existed");
        }

        UserEntity userEntity = UserEntity.builder()
                .email(requestModel.getEmail())
                .name(requestModel.getName())
                .designation(requestModel.getDesignation())
                .contactNumber(requestModel.getContactNumber())
                .password(passwordEncoder.encode(requestModel.getPassword()))
                .role(getRole(requestModel))
                .isActive(true)
                .build();
        UserEntity savedUser = userRepository.save(userEntity);
        AuthenticationResponse authRes = AuthenticationResponse.builder()
                .token(jwtService.generateToken(savedUser))
                .build();
        Map<String, Object> response = new HashMap<>();
        response.put("authRes", authRes);
        response.put("savedUserId", savedUser.getId());

        return response;
    }

    /**
     * Retrieves the role from the user request model.
     *
     * @param model The user request model.
     * @return Role enum value.
     */
    private Role getRole(UserRequestDto model) {
        Role role;
        switch (model.getRole()) {
            case "STAFF" -> role = Role.STAFF;
            case "ADMIN" -> role = Role.ADMIN;
            default -> throw new IllegalArgumentException("Invalid role!");
        }
        return role;
    }

    /**
     * Logs in a user with the provided credentials.
     *
     * @param authenticationDto The authentication request containing email and password.
     * @return AuthenticationResponse containing the generated JWT token.
     */
    public AuthenticationResponse login(AuthenticationDto authenticationDto) {
        UserEntity user1 = userRepository.findByEmail(authenticationDto.getEmail());
        if(user1.isActive())
        {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationDto.getEmail(),
                                authenticationDto.getPassword()
                        )
                );
            } catch (Exception e) {
                throw new EmailPasswordNotMatchException("Wrong Login Credentials");
            }

            var user = userRepository.findByEmail(authenticationDto.getEmail());
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new UserNotActiveException("User not active");
        }
    }

    /**
     * Retrieves a list of all users.
     *
     * @return List of UserEntity.
     */
    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found.");
        }
        return users;
    }

    /**
     * Resets the password for a user with the given ID.
     *
     * @param userId            The ID of the user.
     * @param userRequestModel  The model containing the new password.
     * @return ResponseEntity containing the updated UserEntity.
     */
    @Override
    public ResponseEntity<Object> reseatPassword(Long userId, UserRequestDto userRequestModel) {

        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("Invalid user Id " + userId);
        }
        else {
            UserEntity user1 = user.get();
            user1.setPassword(passwordEncoder.encode(userRequestModel.getPassword()));
            UserEntity saveUser = userRepository.save(user1);
            return ResponseEntity.ok(saveUser);
        }
    }

    /**
     * Changes the active status of a user (either staff or admin) with the given ID.
     *
     * @param userId The ID of the user (staff or admin).
     * @return ResponseEntity containing the status change message.
     */
    @Override
    public ResponseEntity<Object> statusChange(Long userId) {

        Optional<UserEntity> userOptional = userRepository.findById(userId);


        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            user.setActive(!user.isActive());

            userRepository.save(user);
            return ResponseEntity.ok("Staff with ID: " + userId + " has been changed.");
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }
}
