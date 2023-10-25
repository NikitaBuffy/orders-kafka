package ru.pominov.customer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
@Slf4j
public class ExceptionApiHandler {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler
    public ResponseEntity<ApiError> springValidationException(MethodArgumentNotValidException exception) {
        log.warn(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError("BAD_REQUEST", "Incorrectly made request.",
                        exception.getMessage(), LocalDateTime.now().format(DATE_FORMAT)));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> manualValidationException(ValidationException exception) {
        log.debug(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError("BAD_REQUEST", "Incorrectly made request.",
                        exception.getMessage(), LocalDateTime.now().format(DATE_FORMAT)));
    }
}
