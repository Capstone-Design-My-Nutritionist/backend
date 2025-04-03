package com.example.myNutrition.domain.survey.enums.plural;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Allergy {
    NUTS("견과류"),
    DAIRY("유제품"),
    SHELLFISH("갑각류"),
    GLUTEN("글루텐"),
    NONE("해당 없음");

    private final String displayName;
}
