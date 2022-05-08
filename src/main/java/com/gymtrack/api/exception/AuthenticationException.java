package com.gymtrack.api.exception;

//@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid credentials")
//@NoArgsConstructor
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String msg) {
        super(msg);
    }
}
