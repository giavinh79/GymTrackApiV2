package com.gymtrack.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Access denied")
public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Access denied");
    }

    public ForbiddenException(String msg) {
        super(msg);
    }
}
