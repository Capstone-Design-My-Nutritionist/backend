package com.example.myNutrition.domain.user.exception;

import com.example.myNutrition.common.exception.BadRequestException;

public class EmailAlreadyExistsException extends BadRequestException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}