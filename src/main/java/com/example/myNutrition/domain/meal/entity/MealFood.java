package com.example.myNutrition.domain.meal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 음식 이름
    private Double amount; // g 또는 ml

    @ManyToOne(fetch = FetchType.LAZY)
    private MealImage mealImage;

    @OneToOne(mappedBy = "mealFood", cascade = CascadeType.ALL, orphanRemoval = true)
    private NutritionDetail nutritionDetail;

    @Builder
    public MealFood(String name, double amount, MealImage mealImage, NutritionDetail nutritionDetail) {
        this.name = name;
        this.amount = amount;
        this.mealImage = mealImage;
        this.nutritionDetail = nutritionDetail;
    }

    public void setMealImage(MealImage mealImage) {
        this.mealImage = mealImage;
    }
}
