package com.taslim.springSecurity.dto;

import lombok.*;

/**
 * DTO (Data Transfer Object) representing the request model for user authentication.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationDto {

    private String email;    // Email address of the user for authentication.
    private String password; // Password of the user for authentication.

}
