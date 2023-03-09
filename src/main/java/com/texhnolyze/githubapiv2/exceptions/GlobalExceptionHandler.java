package com.texhnolyze.githubapiv2.exceptions;

import com.texhnolyze.githubapiv2.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{


    @ExceptionHandler(MemberRegistrationException.class)
    public ResponseEntity<ApiResponse> handleMemberRegistrationException(MemberRegistrationException ex){
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder()
                .message(message)
                .success(false)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
