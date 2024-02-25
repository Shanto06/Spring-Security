package com.taslim.springSecurity.utils;

import com.taslim.springSecurity.dto.ExceptionModel;
import com.taslim.springSecurity.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Global exception handler to manage exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, UserNotActiveException.class, UserAlreadyExistException.class, EmailPasswordNotMatchException.class})
    public ResponseEntity<Object> returnNotFoundException(Exception ex) {
        if (ex instanceof UserAlreadyExistException) {
            return new ResponseEntity<>(new ExceptionModel(ex.getMessage()), HttpStatus.NOT_IMPLEMENTED);
        } else if (ex instanceof EmailPasswordNotMatchException) {
            return new ResponseEntity<>(new ExceptionModel(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof UserNotActiveException || ex instanceof UserNotFoundException) {
            return new ResponseEntity<>(new ExceptionModel(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ExceptionModel(ex.getMessage()), HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
