package com.example.myNutrition.domain.survey.entity;

import com.example.myNutrition.domain.survey.entity.mappingTable.*;
import com.example.myNutrition.domain.survey.enums.singular.*;
import com.example.myNutrition.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
}