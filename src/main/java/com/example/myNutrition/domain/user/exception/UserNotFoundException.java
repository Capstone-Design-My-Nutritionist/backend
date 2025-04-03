package com.example.myNutrition.domain.user.exception;

import com.example.myNutrition.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}