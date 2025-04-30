package com.example.myNutrition.domain.meal.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class DailyNutritionSummaryResponseDto {
    private LocalDate date;
    private Double totalEnergy;
    private Double totalCarbohydrate;
    private Double totalProtein;
    private Double totalFat;
    private Double totalCalcium;
    private Double totalVitaminA;
    private Double totalVitaminC;
}
