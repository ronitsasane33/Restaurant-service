package com.tmc.restaurant.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodItemServiceException extends  RuntimeException {
    public static final long serialVersionUID = 1L;

    public FoodItemServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public FoodItemServiceException(String message) {
        super(message);
    }

}