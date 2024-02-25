package com.taslim.springSecurity.config;

import com.taslim.springSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class responsible for configuring authentication and authorization settings in the application.
 */
@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final UserRepository userRepository;

    /**
     * Bean for providing a custom UserDetailsService implementation using UserRepository.
     *
     * @return UserDetailsService implementation.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userRepository::findByEmail;
    }

    /**
     * Bean for providing a custom AuthenticationProvider implementation using DaoAuthenticationProvider.
     *
     * @return AuthenticationProvider implementation.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Creating a DaoAuthenticationProvider instance
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // Setting the UserDetailsService for authentication
        authProvider.setUserDetailsService(userDetailsService());
        // Setting the password encoder for authentication
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Bean for providing a custom AuthenticationManager using AuthenticationConfiguration.
     *
     * @param config AuthenticationConfiguration instance.
     * @return AuthenticationManager instance.
     * @throws Exception Exception during AuthenticationManager creation.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Returning the AuthenticationManager from AuthenticationConfiguration
        return config.getAuthenticationManager();
    }

    /**
     * Bean for providing a BCryptPasswordEncoder as the PasswordEncoder.
     *
     * @return BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Creating and returning a BCryptPasswordEncoder instance
        return new BCryptPasswordEncoder();
    }
}
