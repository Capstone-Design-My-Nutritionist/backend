package com.example.myNutrition.domain.user.exception;

import com.example.myNutrition.common.exception.BadRequestException;

public class NicknameAlreadyExistsException extends BadRequestException {
    public NicknameAlreadyExistsException(String message) {
        super(message);
    }
}
