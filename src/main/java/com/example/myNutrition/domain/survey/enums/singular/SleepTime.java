package com.example.myNutrition.domain.survey.enums.singular;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SleepTime {
    UNDER_5_HOURS("5시간 이하"),
    SIX_TO_SEVEN_HOURS("6~7시간"),
    OVER_8_HOURS("8시간 이상");

    private final String displayName;
}
