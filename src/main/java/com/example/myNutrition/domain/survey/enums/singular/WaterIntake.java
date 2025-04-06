package com.example.myNutrition.domain.survey.enums.singular;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WaterIntake {
    UNDER_1L("1L 이하"),
    ONE_TO_TWO_L("1~2L"),
    OVER_2L("2L 이상");

    private final String displayName;
}
