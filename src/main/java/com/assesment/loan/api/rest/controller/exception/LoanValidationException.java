package com.assesment.loan.controller.exception;


public class LoanValidationException extends RuntimeException {
    private final String message;

    private final HttpStatus httpStatus;

    public LoanValidationException(String message, HttpStatus httpStatus) {
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