package com.gymtrack.api.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Invalid Credentials");
    }

    public AuthenticationException(String msg) {
        super(msg);
    }
}
