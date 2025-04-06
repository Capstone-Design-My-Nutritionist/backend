package com.example.myNutrition.domain.survey.enums.singular;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Smoking {
    NON_SMOKER("비흡연"),
    PAST_SMOKER("과거 흡연"),
    CURRENT_SMOKER("현재 흡연");

    private final String displayName;
}