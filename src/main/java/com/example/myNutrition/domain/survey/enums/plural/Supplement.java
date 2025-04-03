package com.example.myNutrition.domain.survey.enums.plural;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Supplement {
    OMEGA3("오메가-3"),
    VITAMIN_K("비타민K"),
    MAGNESIUM("마그네슘"),
    CALCIUM("칼슘"),
    IRON("철분"),
    VITAMIN_C("비타민C"),
    PROBIOTICS("프로바이오틱스"),
    VITAMIN_D("비타민D"),
    GINSENG("홍삼(인삼)");

    private final String displayName;
}
