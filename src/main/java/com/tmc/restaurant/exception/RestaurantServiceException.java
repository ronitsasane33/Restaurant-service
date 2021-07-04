package com.tmc.restaurant.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantServiceException extends  RuntimeException {
    public static final long serialVersionUID = 1L;

    public RestaurantServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public RestaurantServiceException(String message) {
        super(message);
    }

}