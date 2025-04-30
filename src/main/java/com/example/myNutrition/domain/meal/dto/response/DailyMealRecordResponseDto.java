package com.example.myNutrition.domain.meal.dto.response;

import com.example.myNutrition.domain.meal.entity.MealTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        private MealTime mealTime;
        private List<MealFoodDto> foods;

        @Getter
        @Builder
        @AllArgsConstructor
        public static class MealFoodDto {
            private String name;
            private String imageUrl;
            private Double amount;
            private NutritionSummaryDto nutrition;

            @Getter
            @Builder
            @AllArgsConstructor
            public static class NutritionSummaryDto {
                private Double energy;
                private Double carbohydrate;
                private Double protein;
                private Double fat;
            }
        }
    }
}
