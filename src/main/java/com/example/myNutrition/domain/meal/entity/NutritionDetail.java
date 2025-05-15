package com.example.myNutrition.domain.meal.entity;

import com.example.myNutrition.domain.meal.dto.request.MealFoodRegisterRequestDto;
import com.example.myNutrition.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NutritionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double energy;
    private Double carbohydrate;
    private Double protein;
    private Double fat;
    private Double saturatedFat;   // 포화지방산
    private Double transFat;       // 트랜스지방산
    private Double cholesterol;
    private Double sugar;
    private Double dietaryFiber;
    private Double sodium;
    private Double calcium;
    private Double vitaminA;
    private Double vitaminC;
    private Double vitaminD;
    private Double vitaminE;
    private Double vitaminB;       // vitaminB6를 vitaminB로 저장할 경우

    @Builder
    public NutritionDetail(Double energy, Double carbohydrate, Double protein, Double fat,
                           Double saturatedFat, Double transFat, Double cholesterol,
                           Double sugar, Double dietaryFiber, Double sodium, Double calcium,
                           Double vitaminA, Double vitaminC, Double vitaminD, Double vitaminE,
                           Double vitaminB) {
        this.energy = energy;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.saturatedFat = saturatedFat;
        this.transFat = transFat;
        this.cholesterol = cholesterol;
        this.sugar = sugar;
        this.dietaryFiber = dietaryFiber;
        this.sodium = sodium;
        this.calcium = calcium;
        this.vitaminA = vitaminA;
        this.vitaminC = vitaminC;
        this.vitaminD = vitaminD;
        this.vitaminE = vitaminE;
        this.vitaminB = vitaminB;
    }

    public static NutritionDetail from(MealFoodRegisterRequestDto.MealFoodDto.NutritionDetailDto dto) {
        return NutritionDetail.builder()
                .energy(dto.getEnergy())
                .carbohydrate(dto.getCarbohydrate())
                .protein(dto.getProtein())
                .fat(dto.getFat())
                .saturatedFat(dto.getSaturatedFattyAcid())
                .transFat(dto.getTransFattyAcid())
                .cholesterol(dto.getCholesterol())
                .sugar(dto.getTotalSugars())
                .dietaryFiber(dto.getTotalDietaryFiber())
                .sodium(dto.getSodium())
                .calcium(dto.getCalcium())
                .vitaminA(dto.getVitaminA())
                .vitaminC(dto.getVitaminC())
                .vitaminD(dto.getVitaminD())
                .vitaminE(dto.getVitaminE())
                .vitaminB(dto.getVitaminB6())
                .build();
    }
}
