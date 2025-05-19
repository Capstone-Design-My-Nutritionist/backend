package com.example.myNutrition.domain.userNutritiontarget.entity;

import com.example.myNutrition.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNutritionTarget {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    @Builder
    private UserNutritionTarget(User user,
                                Double energy,
                                Double carbohydrate,
                                Double protein,
                                Double fat,
                                Double cholesterol,
                                Double dietaryFiber,
                                Double saturatedFattyAcid,
                                Double transFattyAcid,
                                Double sugars,
                                Double sodium,
                                Double calcium,
                                Double vitaminA,
                                Double vitaminC,
                                Double vitaminD,
                                Double vitaminE,
                                Double vitaminK) {
        this.user = user;
        this.energy = energy;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.cholesterol = cholesterol;
        this.dietaryFiber = dietaryFiber;
        this.saturatedFattyAcid = saturatedFattyAcid;
        this.transFattyAcid = transFattyAcid;
        this.sugars = sugars;
        this.sodium = sodium;
        this.calcium = calcium;
        this.vitaminA = vitaminA;
        this.vitaminC = vitaminC;
        this.vitaminD = vitaminD;
        this.vitaminE = vitaminE;
        this.vitaminK = vitaminK;
    }

    public static UserNutritionTarget of(User user,
                                         Double energy,
                                         Double carbohydrate,
                                         Double protein,
                                         Double fat,
                                         Double cholesterol,
                                         Double dietaryFiber,
                                         Double saturatedFattyAcid,
                                         Double transFattyAcid,
                                         Double sugars,
                                         Double sodium,
                                         Double calcium,
                                         Double vitaminA,
                                         Double vitaminC,
                                         Double vitaminD,
                                         Double vitaminE,
                                         Double vitaminK) {
        return UserNutritionTarget.builder()
                .user(user)
                .energy(energy)
                .carbohydrate(carbohydrate)
                .protein(protein)
                .fat(fat)
                .cholesterol(cholesterol)
                .dietaryFiber(dietaryFiber)
                .saturatedFattyAcid(saturatedFattyAcid)
                .transFattyAcid(transFattyAcid)
                .sugars(sugars)
                .sodium(sodium)
                .calcium(calcium)
                .vitaminA(vitaminA)
                .vitaminC(vitaminC)
                .vitaminD(vitaminD)
                .vitaminE(vitaminE)
                .vitaminK(vitaminK)
                .build();
    }
}
