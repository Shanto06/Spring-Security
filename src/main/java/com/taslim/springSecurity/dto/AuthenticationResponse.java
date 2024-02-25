package com.taslim.springSecurity.dto;

import lombok.*;

/**
 * DTO (Data Transfer Object) representing the response model for user authentication.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    private String token;  // Token generated upon successful user authentication.

}
