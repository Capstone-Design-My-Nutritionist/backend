package com.example.myNutrition.domain.survey.enums.plural;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Disease {
    DIABETES("당뇨병"),
    HYPERTENSION("고혈압"),
    HYPERLIPIDEMIA("고지혈증"),
    THYROID_DISEASE("갑상선 질환"),
    OSTEOPOROSIS("골다공증"),
    ANEMIA("빈혈"),
    GASTRIC_DISORDER("위장 장애"),
    LIVER_DISEASE("간 질환"),
    KIDNEY_DISEASE("신장 질환");

    private final String displayName;
}