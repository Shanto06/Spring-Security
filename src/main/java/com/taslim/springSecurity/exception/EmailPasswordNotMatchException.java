package com.taslim.springSecurity.exception;

/**
 * Custom exception class to indicate that the provided email and password do not match during authentication.
 */
public class EmailPasswordNotMatchException extends RuntimeException {

    /**
     * Constructs a new EmailPasswordNotMatchException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public EmailPasswordNotMatchException(String message) {
        super(message);
    }
}
