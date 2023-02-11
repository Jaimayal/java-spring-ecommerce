package com.jaimayal.tarvinshop.AuthSystem.exception;

import com.jaimayal.tarvinshop.AuthSystem.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseDTO handleUserNotFound(UserNotFoundException e) {
        return new ErrorResponseDTO(
                e.getMessage(), 
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase()
        );
    }
    
    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public ErrorResponseDTO handleCredentialsNotFound(PasswordDoesNotMatchException e) {
        return new ErrorResponseDTO(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(), 
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        );
    }

    @ExceptionHandler(TokenGenerationException.class)
    public ErrorResponseDTO handleTokenGeneration(TokenGenerationException e) {
        return new ErrorResponseDTO(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        );
    }
}
