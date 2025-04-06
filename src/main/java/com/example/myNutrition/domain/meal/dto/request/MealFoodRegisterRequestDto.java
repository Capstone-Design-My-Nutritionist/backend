package com.example.myNutrition.domain.meal.dto.request;

import com.example.myNutrition.domain.meal.entity.MealTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MealFoodRegisterRequestDto {
    private MealTime mealTime;
    private String imageUrl;
    private List<MealFoodDto> foods;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MealFoodDto {
        private String name;
        private double eatAmount;
        private NutritionDetailDto nutrition;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class NutritionDetailDto {
            private Double energy;
            private Double carbohydrate;
            private Double protein;
            private Double fat;
            private Double vitaminA;
            private Double vitaminC;
            private Double calcium;
            // 필요한 영양소 계속 추가 가능
        }
    }
}