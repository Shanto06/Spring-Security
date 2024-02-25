package com.polygon.technology.springSecurity.exception;

/**
 * Custom exception class to indicate that a user is not active.
 */
public class UserNotActiveException extends RuntimeException {

    /**
     * Constructs a new UserNotActiveException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public UserNotActiveException(String message) {
        super(message);
    }
}
