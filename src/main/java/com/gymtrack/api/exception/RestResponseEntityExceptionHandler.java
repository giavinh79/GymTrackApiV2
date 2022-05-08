package com.gymtrack.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Deprecated Disabling this for now
 * I am going to rely on @ResponseStatus in exception classes until more specific status codes or logic is needed
 * To re-enable, add back annotation @ControllerAdvice(basePackages = {"com.gymtrack.api.feature"})
 */
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(RuntimeException exception, WebRequest request) {
        String errorMessage = exception.getMessage() == null ? "Bad Request" : exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(RuntimeException exception, WebRequest request) {
        String errorMessage = exception.getMessage() == null ? "Invalid credentials" : exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException exception, WebRequest request) {
        String errorMessage = exception.getMessage() == null ? "Entity could not be found" : exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
