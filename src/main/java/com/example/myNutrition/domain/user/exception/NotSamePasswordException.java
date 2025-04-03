package com.example.myNutrition.domain.user.exception;

import com.example.myNutrition.common.exception.BadRequestException;

public class NotSamePasswordException extends BadRequestException {

    public NotSamePasswordException(String message) {
        super(message);
    }
}
