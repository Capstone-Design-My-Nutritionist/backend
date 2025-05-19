// UserNutritionTargetResponseDto.java
package com.example.myNutrition.domain.userNutritiontarget.dto;

import com.example.myNutrition.domain.userNutritiontarget.entity.UserNutritionTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserNutritionTargetResponseDto {
    private Double energy;
    private Double carbohydrate;
    private Double protein;
    private Double fat;
    private Double cholesterol;
    private Double dietaryFiber;
    private Double saturatedFattyAcid;
    private Double transFattyAcid;
    private Double sugars;
    private Double sodium;
    private Double calcium;
    private Double vitaminA;
    private Double vitaminC;
    private Double vitaminD;
    private Double vitaminE;
    private Double vitaminK;

    public static UserNutritionTargetResponseDto from(UserNutritionTarget target) {
        return UserNutritionTargetResponseDto.builder()
                .energy(target.getEnergy())
                .carbohydrate(target.getCarbohydrate())
                .protein(target.getProtein())
                .fat(target.getFat())
                .cholesterol(target.getCholesterol())
                .dietaryFiber(target.getDietaryFiber())
                .saturatedFattyAcid(target.getSaturatedFattyAcid())
                .transFattyAcid(target.getTransFattyAcid())
                .sugars(target.getSugars())
                .sodium(target.getSodium())
                .calcium(target.getCalcium())
                .vitaminA(target.getVitaminA())
                .vitaminC(target.getVitaminC())
                .vitaminD(target.getVitaminD())
                .vitaminE(target.getVitaminE())
                .vitaminK(target.getVitaminK())
                .build();
    }
}