package com.subhadeep.e_food_inventory_service.advices;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.subhadeep.e_food_inventory_service.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFounException(ResourceNotFoundException ex) {

        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .error(ex.getMessage())
                        .status(404)
                        .suberros(new ArrayList<>())
                        .build()

        );

    }

    private ResponseEntity<ApiResponseDTO<?>> buildResponseEntityWithApiResponse(ErrorResponse errorResponse) {
        ApiResponseDTO<ErrorResponse> errApiResponse = new ApiResponseDTO<>(errorResponse);
        errApiResponse.setStatus(errorResponse.getStatus());
        return new ResponseEntity<>(errApiResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }
}
