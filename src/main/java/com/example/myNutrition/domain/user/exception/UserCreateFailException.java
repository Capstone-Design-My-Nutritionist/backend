package com.example.myNutrition.domain.user.exception;

public class UserCreateFailException extends RuntimeException {
    public UserCreateFailException(String message) {
        super(message);
    }
}
