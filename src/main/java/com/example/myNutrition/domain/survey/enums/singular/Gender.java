package com.example.myNutrition.domain.survey.enums.singular;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private final String displayName;
}

