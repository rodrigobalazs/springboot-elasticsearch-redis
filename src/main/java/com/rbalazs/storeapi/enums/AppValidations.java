package com.rbalazs.storeapi.enums;

import org.springframework.http.HttpStatus;

/**
 * Enum which contains some validation messages.
 */
public enum AppValidations {
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "the entity was not found in the application");

    private final HttpStatus httpStatus;
    private final String message;

    AppValidations(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
