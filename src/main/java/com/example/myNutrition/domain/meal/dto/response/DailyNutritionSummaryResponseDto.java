package com.example.myNutrition.domain.meal.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class DailyNutritionSummaryResponseDto {
    private LocalDate date;
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

    public static DailyNutritionSummaryResponseDto from(
            LocalDate date,
            double energy,
            double carbohydrate,
            double protein,
            double fat,
            double saturatedFattyAcid,
            double transFattyAcid,
            double cholesterol,
            double totalSugars,
            double totalDietaryFiber,
            double sodium,
            double calcium,
            double vitaminA,
            double vitaminC,
            double vitaminD,
            double vitaminE,
            double vitaminB6
    ) {
        return DailyNutritionSummaryResponseDto.builder()
                .date(date)
                .energy(energy)
                .carbohydrate(carbohydrate)
                .protein(protein)
                .fat(fat)
                .saturatedFattyAcid(saturatedFattyAcid)
                .transFattyAcid(transFattyAcid)
                .cholesterol(cholesterol)
                .totalSugars(totalSugars)
                .totalDietaryFiber(totalDietaryFiber)
                .sodium(sodium)
                .calcium(calcium)
                .vitaminA(vitaminA)
                .vitaminC(vitaminC)
                .vitaminD(vitaminD)
                .vitaminE(vitaminE)
                .vitaminB6(vitaminB6)
                .build();
    }
}

