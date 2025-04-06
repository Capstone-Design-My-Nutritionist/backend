package com.example.myNutrition.domain.survey.exception;

import com.example.myNutrition.common.exception.BadRequestException;


public class DuplicateSurveyException extends BadRequestException {
    public DuplicateSurveyException(String message) {
        super(message);
    }
}
