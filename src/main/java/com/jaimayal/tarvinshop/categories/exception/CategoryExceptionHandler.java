package com.jaimayal.tarvinshop.categories.exception;

import com.jaimayal.tarvinshop.categories.dto.CategoryErrorResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryExceptionHandler {
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CategoryErrorResponseDTO handleProductNotFound(CategoryNotFoundException e) {
        return new CategoryErrorResponseDTO(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase()
        );
    }
}
