package com.example.myNutrition.domain.survey.enums.plural;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Medication {
    BLOOD_PRESSURE("혈압"),
    ANTICOAGULANT("혈액응고제"),
    DIABETES("당뇨"),
    THYROID("갑상선"),
    STEROID("스테로이드"),
    GASTRIC("위장"),
    DEPRESSION("우울증"),
    CONTRACEPTIVE("피임"),
    PAINKILLER("진통제");

    private final String displayName;
}

