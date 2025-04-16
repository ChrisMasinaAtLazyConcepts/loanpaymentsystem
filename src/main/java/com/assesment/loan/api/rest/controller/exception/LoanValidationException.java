package com.assesment.loan.api.rest.controller.exception;

import org.springframework.http.HttpStatus;

public class LoanValidationException extends RuntimeException {
    private final String message;

    private final HttpStatus httpStatus;

    public LoanValidationException(String message,HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}

class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}