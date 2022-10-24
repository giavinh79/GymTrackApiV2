package com.gymtrack.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity was not found")
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Entity was not found");
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
