package com.example.myNutrition.domain.survey.enums.singular;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExerciseFrequency {
    NONE("거의 하지 않음"),
    LIGHT_1_3("1~3회 가벼운 운동"),
    MODERATE_3_5("3~5회 보통 강도 운동"),
    INTENSE_6_7("6~7회 강도 높은 운동"),
    DAILY_ACTIVE("매일 (활동적이거나 운동선수)");

    private final String displayName;
}
