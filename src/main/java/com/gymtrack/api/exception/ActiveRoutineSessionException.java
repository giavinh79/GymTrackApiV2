package com.gymtrack.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Action cannot be performed as user has an active routine session")
public class ActiveRoutineSessionException extends RuntimeException {
    public ActiveRoutineSessionException() {
        super("Action cannot be performed as user has an active routine session");
    }
}
