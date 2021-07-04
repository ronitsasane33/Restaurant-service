package com.tmc.restaurant.exception.handler;


import com.tmc.restaurant.exception.RestaurantServiceException;
import com.tmc.restaurant.response.Response;
import com.tmc.restaurant.response.ResponseMetadata;
import com.tmc.restaurant.response.StatusMessage;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RestaurantServiceException.class)
    public ResponseEntity<Response<?>> handleEmployeesServiceException(RestaurantServiceException e) {
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Response<?>> buildResponse(StatusMessage statusMessage, HttpStatus status) {
        var response = Response.builder()
                .meta(ResponseMetadata.builder()
                        .statusMessage(statusMessage.name())
                        .statusCode(status.value())
                        .build())
                .build();
        return ResponseEntity.status(status)
                .body(response);
    }
}