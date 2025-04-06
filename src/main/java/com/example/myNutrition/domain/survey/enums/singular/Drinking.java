package com.example.myNutrition.domain.survey.enums.singular;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Drinking {
    NEVER("전혀 안함"),
    SOMETIMES("가끔"),
    ONE_TWO_WEEKLY("주 1~2회"),
    THREE_MORE_WEEKLY("주 3회 이상");

    private final String displayName;
}
