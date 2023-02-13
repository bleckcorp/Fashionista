package com.bctech.fashionista.exceptions;

import com.bctech.fashionista.dto.response.ApiResponse;
import com.bctech.fashionista.exceptions.customexceptions.ApiGenericException;
import com.bctech.fashionista.exceptions.customexceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ApiResponse<ErrorDetails>> handleCustomException(CustomException ex, WebRequest request) {
        int statusCode = ex.getStatus().value() != 0 ? ex.getStatus().value() : HttpStatus.PRECONDITION_FAILED.value();
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(ex.getMessage())
                .code(statusCode)
                .details(request.getDescription(true))
                .timestamp(LocalDate.now())
                .build();
        return ResponseEntity.status(errorDetails.getCode()).body(ApiResponse.<ErrorDetails>builder()
                .message(errorDetails.getMessage())
                .status(HttpStatus.valueOf(statusCode))
                .error(errorDetails)
                .build());
    }

    @ExceptionHandler(value = {ApiGenericException.class})
    public ResponseEntity<Object> handleApiException(ApiGenericException exception) {
        return ResponseEntity.status(400).body(exception);
    }


}
