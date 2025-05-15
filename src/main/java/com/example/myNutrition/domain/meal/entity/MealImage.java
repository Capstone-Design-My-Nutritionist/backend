package com.example.myNutrition.domain.meal.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private MealRecord mealRecord;

    @OneToMany(mappedBy = "mealImage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealFood> mealFoods = new ArrayList<>();

    @Builder
    public MealImage(String imageUrl, MealRecord mealRecord) {
        this.imageUrl = imageUrl;
        this.mealRecord = mealRecord;
    }

    // MealImage.java
    public void addMealFood(MealFood food) {
        this.mealFoods.add(food);
        food.setMealImage(this);
    }

    public void setMealRecord(MealRecord mealRecord) {
        this.mealRecord = mealRecord;
    }

    public void removeMealFood(MealFood food) {
        this.mealFoods.remove(food);
        food.setMealImage(null); // 연관관계 끊기
    }
}
