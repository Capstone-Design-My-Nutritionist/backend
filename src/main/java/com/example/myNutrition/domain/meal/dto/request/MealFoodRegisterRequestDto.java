package com.example.myNutrition.domain.meal.dto.request;

import com.example.myNutrition.domain.meal.entity.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MealFoodRegisterRequestDto {
    private MealType mealType;
    private String imageUrl;
    private List<MealFoodDto> foods;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MealFoodDto {
        private String name;
        private String fullName;
        private double eatAmount;
        private NutritionDetailDto nutrition;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class NutritionDetailDto {
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
        }
    }
}