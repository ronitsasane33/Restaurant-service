package com.tmc.restaurant.exception.handler;


import com.tmc.restaurant.exception.MenuServiceException;
import com.tmc.restaurant.exception.RestaurantServiceException;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RestaurantServiceException.class)
    public ResponseEntity<Response<?>> handleRestaurantException(RestaurantServiceException e) {
        log.error(e.getClass().getSimpleName(), e.getMessage());
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(MenuServiceException.class)
    public ResponseEntity<Response<?>> handleMenuException(RestaurantServiceException e) {
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(MenuServiceException.class)
    public ResponseEntity<Response<?>> handleFoodItemException(RestaurantServiceException e) {
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<Response<?>> buildResponse(StatusMessage statusMessage, HttpStatus status, String message) {
        var response = Response.builder()
                .meta(ResponseMetadata.builder()
                        .statusMessage(statusMessage.name())
                        .statusCode(status.value())
                        .build())
                .data(message)
                .build();
        return ResponseEntity.status(status)
                .body(response);
    }
}