package com.onlineShop.exception;

import com.onlineShop.exception.auth.HandleInvalidCredentials;
import com.onlineShop.exception.auth.HandleInvalidRefreshToken;
import com.onlineShop.exception.auth.HandleUserAlreadyExists;
import com.onlineShop.exception.product.HandleNotEnoughProductQuantity;
import com.onlineShop.exception.product.HandleProductNotFound;
import com.onlineShop.exception.user.HandleInvalidOldPassword;
import com.onlineShop.exception.user.HandleUserNotFound;
import com.onlineShop.exception.user.HandleUserNotFoundByEmail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

    @ExceptionHandler(HandleUserNotFoundByEmail.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundByEmail(HandleUserNotFoundByEmail exception,
                                                                   HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .error("Not found")
                .status(404)
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HandleInvalidOldPassword.class)
    public ResponseEntity<ErrorResponse> handleInvalidOldPassword(HandleInvalidOldPassword exception,
                                                                  HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(400)
                .error("Bad request")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
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


    @ExceptionHandler(HandleInvalidRefreshToken.class)
    public ResponseEntity<ErrorResponse> handleInvalidRefreshToken(HandleInvalidRefreshToken exception,
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException exception,
                                                            HttpServletRequest request){
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(403)
                .error("Forbidden")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(HandleProductNotFound.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(HandleProductNotFound exception,
                                                           HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .error("Not found")
                .status(404)
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HandleNotEnoughProductQuantity.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughProductQuantity(HandleNotEnoughProductQuantity exception,
                                                               HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .error("Bad request")
                .status(400)
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
