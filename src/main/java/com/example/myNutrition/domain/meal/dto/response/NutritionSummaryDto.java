package com.example.myNutrition.domain.meal.dto.response;

import com.example.myNutrition.domain.meal.entity.NutritionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NutritionSummaryDto {
    private Double energy;               // 에너지(kcal)
    private Double carbohydrate;         // 탄수화물(g)
    private Double protein;              // 단백질(g)
    private Double fat;                  // 지방(g)
    private Double saturatedFattyAcid;   // 포화지방산(g)
    private Double transFattyAcid;       // 트랜스지방산(g)
    private Double cholesterol;          // 콜레스테롤(mg)
    private Double totalSugars;          // 당류(g)
    private Double totalDietaryFiber;    // 식이섬유(g)
    private Double sodium;               // 나트륨(mg)
    private Double calcium;              // 칼슘(mg)
    private Double vitaminA;             // 비타민A(μg RE)
    private Double vitaminC;             // 비타민C(mg)
    private Double vitaminD;             // 비타민D(μg)
    private Double vitaminE;             // 비타민E(mg α-TE)
    private Double vitaminB6;            // 비타민B6(mg)


    public static NutritionSummaryDto from(NutritionDetail detail) {
        if (detail == null) {
            return NutritionSummaryDto.builder().build(); // 모든 값 null 또는 0.0으로 초기화
        }

        return NutritionSummaryDto.builder()
                .energy(nullSafe(detail.getEnergy()))
                .carbohydrate(nullSafe(detail.getCarbohydrate()))
                .protein(nullSafe(detail.getProtein()))
                .fat(nullSafe(detail.getFat()))
                .saturatedFattyAcid(nullSafe(detail.getSaturatedFat()))
                .transFattyAcid(nullSafe(detail.getTransFat()))
                .cholesterol(nullSafe(detail.getCholesterol()))
                .totalSugars(nullSafe(detail.getSugar()))
                .totalDietaryFiber(nullSafe(detail.getDietaryFiber()))
                .sodium(nullSafe(detail.getSodium()))
                .calcium(nullSafe(detail.getCalcium()))
                .vitaminA(nullSafe(detail.getVitaminA()))
                .vitaminC(nullSafe(detail.getVitaminC()))
                .vitaminD(nullSafe(detail.getVitaminD()))
                .vitaminE(nullSafe(detail.getVitaminE()))
                .vitaminB6(nullSafe(detail.getVitaminB()))
                .build();
    }

    private static Double nullSafe(Double value) {
        return value != null ? value : 0.0;
    }
}
