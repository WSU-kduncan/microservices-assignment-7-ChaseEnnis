package com.wsu.workoutservice.exception;

public class DatabaseErrorException extends RuntimeException {

    public DatabaseErrorException(String message, Throwable e) {
        super(message, e);
    }


}