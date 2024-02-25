package com.polygon.technology.springSecurity.dto;

import lombok.*;

/**
 * DTO (Data Transfer Object) representing a model for handling exceptions.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionModel {

    private String errorMessage;  // Error message describing the exception.

}
