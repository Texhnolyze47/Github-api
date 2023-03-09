package com.texhnolyze.githubapiv2.exceptions;

import org.springframework.http.converter.HttpMessageNotReadableException;

public class MemberRegistrationException extends RuntimeException {
    public MemberRegistrationException(String message) {
        super(message);
    }
}
