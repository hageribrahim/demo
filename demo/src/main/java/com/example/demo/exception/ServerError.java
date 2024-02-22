package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum ServerError {

    CODE_INVALID_DEPARTMENT("Invalid code Department"),
    CODE_REPEATED_EMPLOYEE("This code already taken"),
    EMPLOYEE_NOT_FOUND("Employee not found"),
    INTERNAL_SERVER_ERROR("Internal server error");

    public enum Type {
        ERROR, WARNING, INFO
    }

    private Type type;
    private transient HttpStatus status;
    private String message;


    ServerError(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    ServerError(String message, HttpStatus status) {
        this(message, Type.ERROR, status);
    }

    ServerError(String message, Type t, HttpStatus status) {
        this.type = t;
        this.status = status;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
