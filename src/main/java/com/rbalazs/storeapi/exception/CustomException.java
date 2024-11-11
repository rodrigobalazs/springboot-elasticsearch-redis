package com.rbalazs.storeapi.exception;

import com.rbalazs.storeapi.enums.AppValidations;
import org.springframework.http.HttpStatus;

/**
 * Custom Exception, the error messages/status codes could be taken from {@link AppValidations}
 */
public class CustomException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public CustomException(AppValidations appValidations) {
        this.status = appValidations.getHttpStatus();
        this.message = appValidations.getMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}