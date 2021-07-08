package com.tmc.restaurant.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationServiceException extends  RuntimeException {
    public static final long serialVersionUID = 1L;

    public ReservationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public ReservationServiceException(String message) {
        super(message);
    }

}