package com.rbalazs.storeapi.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Used to intercept {@link CustomException}
 *
 * @author Rodrigo Balazs
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Intercepts a given {@link CustomException} in order to return to the view an HTTP RESPONSE with the exception
     * message.
     *
     * @return an HTTP RESPONSE with a given HTTP Status and a JSON body with the exception message e.g =>
     * {the entity was not found in the application}
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}