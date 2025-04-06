package com.example.myNutrition.domain.survey.dto.response;

import com.example.myNutrition.domain.survey.entity.Survey;
import com.example.myNutrition.domain.survey.entity.mappingTable.*;
import com.example.myNutrition.domain.survey.enums.plural.*;
import com.example.myNutrition.domain.survey.enums.singular.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SurveyResponseDto {
    private Long id;
    private int age;
    private int height;
    private int weight;
    private boolean takingMedication;
    private boolean takingSupplements;
    private boolean hasDiagnosedDisease;
    private boolean hasFamilyHistory;
    private boolean hasAllergy;
    private Gender gender;
    private SleepTime sleepTime;
    private ExerciseFrequency exerciseFrequency;
    private Integer mealCount;
    private VegetableFruitIntake vegetableFruitIntake;
    private WaterIntake waterIntake;
    private Drinking drinking;
    private Smoking smoking;
    private List<Medication> medications;
    private List<Supplement> supplements;
    private List<Disease> diseases;
    private List<FamilyHistory> familyDiseases;
    private List<Allergy> allergies;
    private List<HealthConcern> concerns;
    private List<HealthGoal> healthGoals;

    public static SurveyResponseDto from(Survey survey) {
        return new SurveyResponseDto(
                survey.getId(),
                survey.getAge(),
                survey.getHeight(),
                survey.getWeight(),
                survey.isTakingMedication(),
                survey.isTakingSupplements(),
                survey.isHasDiagnosedDisease(),
                survey.isHasFamilyHistory(),
                survey.isHasAllergy(),
                survey.getGender(),
                survey.getSleepTime(),
                survey.getExerciseFrequency(),
                survey.getMealCount(),
                survey.getVegetableFruitIntake(),
                survey.getWaterIntake(),
                survey.getDrinking(),
                survey.getSmoking(),
                survey.getMedications().stream().map(SurveyMedication::getMedication).toList(),
                survey.getSupplements().stream().map(SurveySupplement::getSupplement).toList(),
                survey.getDiseases().stream().map(SurveyDisease::getDisease).toList(),
                survey.getFamilyHistories().stream().map(SurveyFamilyHistory::getDisease).toList(),
                survey.getAllergies().stream().map(SurveyAllergy::getAllergy).toList(),
                survey.getConcerns().stream().map(SurveyConcern::getConcern).toList(),
                survey.getHealthGoals().stream().map(SurveyHealthGoal::getGoal).toList()
        );
    }
}