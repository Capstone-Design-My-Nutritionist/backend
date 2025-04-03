package com.example.myNutrition.domain.survey.enums.singular;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VegetableFruitIntake {
    RARELY("거의 먹지 않음"),
    SOMETIMES("가끔 먹음"),
    DAILY("매일 먹음");

    private final String displayName;
}
