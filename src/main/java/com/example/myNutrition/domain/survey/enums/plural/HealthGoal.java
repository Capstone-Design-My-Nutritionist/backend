package com.example.myNutrition.domain.survey.enums.plural;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 건강 목표 항목
@Getter
@AllArgsConstructor
public enum HealthGoal {
    WEIGHT_CONTROL("체중 조절"),
    MUSCLE_GAIN("근육 증가"),
    ENERGY("에너지 충전"),
    BLOOD_PRESSURE_CONTROL("혈압 조절"),
    DIGESTIVE_HEALTH("소화 개선"),
    IMMUNE_SUPPORT("면역력 강화");

    private final String displayName;
}