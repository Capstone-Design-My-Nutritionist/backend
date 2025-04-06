package com.example.myNutrition.domain.survey.enums.plural;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HealthConcern {
    FATIGUE("피로감"),
    WEIGHT_CONTROL("체중 조절"),
    DIGESTIVE_PROBLEM("소화 문제"),
    IMMUNITY("면역력 저하"),
    SLEEP("수면 문제"),
    SKIN_PROBLEM("피부 문제");

    private final String displayName;
}

