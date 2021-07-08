package com.tmc.restaurant.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceException extends  RuntimeException {
    public static final long serialVersionUID = 1L;

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserServiceException(String message) {
        super(message);
    }

}