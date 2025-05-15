package com.example.myNutrition.domain.meal.dto.response;

import com.example.myNutrition.domain.meal.entity.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class DailyMealRecordResponseDto {
    private LocalDate date;
    private List<MealRecordDto> meals;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MealRecordDto {
        private MealType mealTime;
        private List<MealFoodDto> foods;

        @Getter
        @Builder
        @AllArgsConstructor
        public static class MealFoodDto {
            private String name;
            private String imageUrl;
            private Double amount;
            private NutritionSummaryDto nutrition;

        }
    }
}
