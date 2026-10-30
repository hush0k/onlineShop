package com.onlineShop.exception;

import com.onlineShop.exception.auth.HandleInvalidCredentials;
import com.onlineShop.exception.auth.HandleUserAlreadyExists;
import com.onlineShop.exception.user.HandleUserNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HandleUserNotFound.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(HandleUserNotFound exception, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .error("Not found")
                .status(404)
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HandleUserAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleUsersAlreadyExists(HandleUserAlreadyExists exception,
                                                                  HttpServletRequest request){
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(409)
                .error("Conflict")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(HandleInvalidCredentials.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(HandleInvalidCredentials exception,
                                                                  HttpServletRequest request){
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(401)
                .error("Unauthorized")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

}
