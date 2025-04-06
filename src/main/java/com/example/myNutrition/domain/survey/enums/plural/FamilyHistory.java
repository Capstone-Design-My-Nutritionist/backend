package com.example.myNutrition.domain.survey.enums.plural;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FamilyHistory {
    DIABETES("당뇨병"),
    HYPERTENSION("고혈압"),
    CARDIOVASCULAR("심혈관 질환"),
    THYROID_DISEASE("갑상선 질환"),
    OSTEOPOROSIS("골다공증"),
    HYPERLIPIDEMIA("고지혈증"),
    CANCER("암"),
    ALZHEIMER("알츠하이머"),
    KIDNEY_DISEASE("신장 질환");

    private final String displayName;
}
