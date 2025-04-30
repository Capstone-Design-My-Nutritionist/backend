package com.example.myNutrition.domain.meal.entity;

import com.example.myNutrition.common.BaseTimeEntity;
import com.example.myNutrition.domain.user.entity.User;
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
public class MealRecord extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MealTime mealTime;

    @OneToMany(mappedBy = "mealRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealImage> mealImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public MealRecord(MealTime mealTime, User user) {
        this.mealTime = mealTime;
        this.user = user;
    }

    // MealRecord.java
    public void addMealImage(MealImage image) {
        this.mealImages.add(image);
        image.setMealRecord(this);
    }

}

