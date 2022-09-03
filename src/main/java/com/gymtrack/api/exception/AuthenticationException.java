package com.gymtrack.api.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Unauthorized");
    }

    public AuthenticationException(String msg) {
        super(msg);
    }
}
