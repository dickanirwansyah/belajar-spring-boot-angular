package com.generali.jwtauthbackend.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.generali.jwtauthbackend.exception.ResourceConflictException;
import com.generali.jwtauthbackend.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> internalServerError(Exception e, WebRequest request){
        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorMessage> conflictError(ResourceConflictException e, WebRequest request){
        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .code(HttpStatus.CONFLICT.value())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundError(ResourceNotFoundException e, WebRequest request){
        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorMessage implements Serializable {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        private LocalDateTime timestamp;
        private int code;
        private String message;
    }
}
