package com.example.myNutrition.domain.survey.entity;

import com.example.myNutrition.domain.survey.entity.mappingTable.*;
import com.example.myNutrition.domain.survey.enums.singular.*;
import com.example.myNutrition.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean completed = false;

    private int age;
    private int height;
    private int weight;

    private boolean takingMedication;
    private boolean takingSupplements;
    private boolean hasDiagnosedDisease;
    private boolean hasFamilyHistory;
    private boolean hasAllergy;


    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SleepTime sleepTime;

    @Enumerated(EnumType.STRING)
    private ExerciseFrequency exerciseFrequency;

    private Integer mealCount;

    @Enumerated(EnumType.STRING)
    private VegetableFruitIntake vegetableFruitIntake;

    @Enumerated(EnumType.STRING)
    private WaterIntake waterIntake;

    @Enumerated(EnumType.STRING)
    private Drinking drinking;

    @Enumerated(EnumType.STRING)
    private Smoking smoking;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyMedication> medications = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveySupplement> supplements = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyDisease> diseases = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyFamilyHistory> familyHistories = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyAllergy> allergies = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyConcern> concerns = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyHealthGoal> healthGoals = new ArrayList<>();

    @OneToOne
    private User user;

    @Builder
    public Survey(User user) {
        this.user = user;
    }


    //설문 조사별 각 Patch 메서드
    public void updateGender(Gender gender) {
        this.gender = gender;
    }
    public void updateAge(int age) {
        this.age = age;
    }
    public void updateHeight(int height) {
        this.height = height;
    }
    public void updateWeight(int weight) {
        this.weight = weight;
    }

    //boolean
    public void updateTakingMedication(boolean takingMedication) {
        this.takingMedication = takingMedication;
    }
    public void updateTakingSupplements(boolean takingSupplements) {
        this.takingSupplements = takingSupplements;
    }
    public void updateHasDiagnosedDisease(boolean hasDiagnosedDisease) {
        this.hasDiagnosedDisease = hasDiagnosedDisease;
    }
    public void updateHasFamilyHistory(boolean hasFamilyHistory) {
        this.hasFamilyHistory = hasFamilyHistory;
    }
    public void updateHasAllergy(boolean hasAllergy) {
        this.hasAllergy = hasAllergy;
    }

    //Enum


    //단일 입력

    public void updateSleepTime(SleepTime sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void updateExerciseFrequency(ExerciseFrequency frequency) {
        this.exerciseFrequency = frequency;
    }

    public void updateMealCount(Integer mealCount) {
        this.mealCount = mealCount;
    }

    public void updateVegetableFruitIntake(VegetableFruitIntake intake) {
        this.vegetableFruitIntake = intake;
    }

    public void updateWaterIntake(WaterIntake waterIntake) {
        this.waterIntake = waterIntake;
    }

    public void updateDrinking(Drinking drinking) {
        this.drinking = drinking;
    }

    public void updateSmoking(Smoking smoking) {
        this.smoking = smoking;
    }


    //설문조사 종료
    public void completeSurvey() {
        this.completed = true;
    }

}