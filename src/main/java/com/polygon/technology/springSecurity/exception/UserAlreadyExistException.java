package com.polygon.technology.springSecurity.exception;

/**
 * Custom exception class to indicate that a user with a given email already exists.
 */
public class UserAlreadyExistException extends RuntimeException {

    /**
     * Constructs a new UserAlreadyExistException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
