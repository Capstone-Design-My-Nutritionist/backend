package com.example.myNutrition.domain.meal.entity;

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
    private Double cholesterol;
    private Double dietaryFiber;
    private Double calcium;
    private Double saturatedFat;
    private Double sodium;
    private Double sugar;
    private Double transFat;
    private Double vitaminA;
    private Double vitaminB;
    private Double vitaminC;
    private Double vitaminD;
    private Double vitaminE;

    @OneToOne(fetch = FetchType.LAZY)
    private MealFood mealFood;

    @OneToOne(fetch = FetchType.LAZY)
    private SupplementIntake supplementIntake;

    @Builder
    public NutritionDetail(Double energy, Double carbohydrate, Double protein, Double fat,
                           Double cholesterol, Double dietaryFiber, Double calcium, Double saturatedFat,
                           Double sodium, Double sugar, Double transFat, Double vitaminA, Double vitaminB,
                           Double vitaminC, Double vitaminD, Double vitaminE) {
        this.energy = energy;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.cholesterol = cholesterol;
        this.dietaryFiber = dietaryFiber;
        this.calcium = calcium;
        this.saturatedFat = saturatedFat;
        this.sodium = sodium;
        this.sugar = sugar;
        this.transFat = transFat;
        this.vitaminA = vitaminA;
        this.vitaminB = vitaminB;
        this.vitaminC = vitaminC;
        this.vitaminD = vitaminD;
        this.vitaminE = vitaminE;
    }

    public void setMealFood(MealFood mealFood) {
        this.mealFood = mealFood;
    }

    public void setSupplementIntake(SupplementIntake supplementIntake) {
        this.supplementIntake = supplementIntake;
    }
}
